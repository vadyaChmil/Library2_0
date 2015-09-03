package com.rhcloud.vadyazakusylo.library.dao_sql;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.rhcloud.vadyazakusylo.library.entity.Book;
import com.rhcloud.vadyazakusylo.library.exception.DataFormatException;
import com.rhcloud.vadyazakusylo.library.exception.DataSizeException;
import com.rhcloud.vadyazakusylo.library.exception.UploadException;

@Component
public class BookBrowser {

	public List<Book> uploadBooks(HttpServletRequest request, HttpServletResponse response)
			throws DataFormatException, UploadException, DataSizeException {
		try {
			String fileContent = parseBrowserInputStreamToString(request, response);
			List<String> elementsOfBook = parseStringToList(fileContent);
			List<Book> booksList = createBookList(elementsOfBook);
			return booksList;
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new DataFormatException("Incorrect data in the uploaded file");
		} catch (IOException e) {
			throw new UploadException("Problem with uploading file");
		}
	}

	private String parseBrowserInputStreamToString(HttpServletRequest request, HttpServletResponse response)
			throws IOException, DataSizeException {
		byte[] repository = parseInputStreamToRepository(request, response);
		String fileContent = parseRepositoryToString(request, repository);
		return fileContent;
	}

	private byte[] parseInputStreamToRepository(HttpServletRequest request, HttpServletResponse response)
			throws IOException, DataSizeException {
		response.setContentType("text/plain");
		BufferedInputStream bufferedInputStream = new BufferedInputStream((InputStream) request.getInputStream());

		byte[] repository;
		final int sizeTemporaryRepository = 1024 * 1024;
		byte[] temporaryRepository = new byte[sizeTemporaryRepository];

		int indexTemporaryRepository = 0;
		int contentInputStream;
		try {
			while ((contentInputStream = bufferedInputStream.read()) != -1) {
				temporaryRepository[indexTemporaryRepository] = (byte) contentInputStream;
				indexTemporaryRepository++;
			}
		} catch (Exception e) {
			throw new DataSizeException("The size of the data file is more than 1 Mb");
		}

		repository = new byte[indexTemporaryRepository];
		for (int index = 0; index < indexTemporaryRepository; index++) {
			repository[index] = temporaryRepository[index];
		}
		temporaryRepository = null;
		return repository;
	}

	private String parseRepositoryToString(HttpServletRequest request, byte[] repository) {
		String boundary = extractBoundary(request.getHeader("Content-Type"));

		List<ResponseBlock> responseBlocks = divideRepositoryToBlocks(repository, boundary);

		String result = null;
		for (ResponseBlock responseBlock : responseBlocks)
			if (!responseBlock.nameBlock.equals("NO_BODY")) {
				result = new String(repository, responseBlock.startIndex,
						responseBlock.endIndex - responseBlock.startIndex);
			}
		return result;
	}

	class ResponseBlock {
		public String nameBlock;
		public int startIndex;
		public int endIndex;

		public ResponseBlock(String filename, int startIndex, int endIndex) {
			this.nameBlock = filename;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}
	}

	private String extractBoundary(String header) {
		int indexBoundaryAttribute = header.lastIndexOf("boundary="); // 9
																		// symbols
		int countBoundaryAttribute = 9;
		String boundary = header.substring(indexBoundaryAttribute + countBoundaryAttribute);
		// real boundary has extra two symbols '-'
		// in the real length than in header Content-Type
		boundary = "--" + boundary;
		return boundary;
	}

	private List<ResponseBlock> divideRepositoryToBlocks(byte[] repository, String boundary) {
		List<ResponseBlock> repositoryArray = new ArrayList<>();

		String repositoryString = new String(repository);
		int operation = 0;
		int indexOfBlock = 0;
		int previousIndexOfBlock = 0;

		// content and boundary are separated by {'\r','\n'}
		int boundarySeparator = 2;

		while ((indexOfBlock = repositoryString.indexOf(boundary, indexOfBlock)) != -1) {
			if (operation != 0) {
				ResponseBlock responseBlock = new ResponseBlock("NO_FILE", previousIndexOfBlock,
						indexOfBlock - boundarySeparator);
				pushDataToBlock(responseBlock, repositoryString.substring(previousIndexOfBlock, indexOfBlock));
				repositoryArray.add(responseBlock);
			}
			indexOfBlock += boundary.length();
			previousIndexOfBlock = indexOfBlock;
			operation++;
		}
		return repositoryArray;
	}

	private void pushDataToBlock(ResponseBlock responseBlock, String repositoryString) {
		char[] charSeparator = { '\r', '\n', '\r', '\n' };
		String stringSeparator = new String(charSeparator);
		String header;

		// content and header are separated by {'\r','\n','\r','\n'}
		int headerSeparator = 4;
		// content and boundary are separated by {'\r','\n'}
		int boundarySeparator = 2;

		int index = repositoryString.indexOf(stringSeparator, boundarySeparator);
		if (index != -1) {
			header = repositoryString.substring(0, index);
			responseBlock.nameBlock = getName(header);
			responseBlock.startIndex += index + headerSeparator;
		}
	}

	private String getName(String header) {
		String filename = "";
		header.toLowerCase();
		int indexHeaderAttribute = header.indexOf("filename=");
		if (indexHeaderAttribute != -1) {
			filename = "BODY";
		} else
			filename = "NO_BODY";
		return filename;
	}

	List<String> parseStringToList(String fileString) {
		List<String> contentString = new ArrayList<String>();
		StringBuilder string = new StringBuilder();

		char[] fileCharArray = fileString.toCharArray();
		for (char element : fileCharArray) {
			if (element != ';' && element != 0x0d) {
				string.append(element);
			} else {
				if (!string.toString().trim().isEmpty()) {
					contentString.add(string.toString().trim());
				}
				string.delete(0, string.length());
			}
		}
		if (!string.toString().trim().isEmpty()) {
			contentString.add(string.toString().trim());
		}
		return contentString;
	}

	List<Book> createBookList(List<String> contentString) throws NumberFormatException, IndexOutOfBoundsException {
		List<Book> uploadBookList = new ArrayList<Book>();
		for (int index = 0; index < contentString.size(); index++) {
			int code = Integer.parseInt(contentString.get(index));
			String autor = contentString.get(++index);
			String title = contentString.get(++index);
			int yearEdition = Integer.parseInt(contentString.get(++index));
			int pages = Integer.parseInt(contentString.get(++index));
			uploadBookList.add(new Book(code, autor, title, yearEdition, pages));
		}
		return uploadBookList;
	}
}