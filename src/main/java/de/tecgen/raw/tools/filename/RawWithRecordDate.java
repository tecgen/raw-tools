package de.tecgen.raw.tools.filename;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.text.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * With help of this class you are able to rename existing RAW files from Magic Lantern to
 * a files containing the date of your recording. <br/>
 * <br/><br/>
 * 
 * <b>Example:</b> <br/><br/>
 * before: <code>M04-1823.RAW </code><br/>
 * after : <code>2013-09-29_1823_(M40-1823).RAW <code><br/>
 * 
 * @author Marco Pehla, tecgen.de
 * @date 2013-09-29
 */
public class RawWithRecordDate implements Runnable {

	private static Logger log = LoggerFactory.getLogger(RawWithRecordDate.class);
	private File file;
	private String fileName;
	private String fileExtention;
	private String fileCreationDate;
	private final String regexDay = "([0-2]?[1-9]|3[01])";
	private final String regexTime = "([01]?[0-9]|2[0-3])[0-5][0-9]";
	private final String patternTime = "^" + regexTime + "$";
	private final String patternMagicLanternRawFileName = "^M" + regexDay
			+ "-" + regexTime + "$";
	private final SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * supposed to be only used by unit tests
	 */
	protected RawWithRecordDate() { }

	public RawWithRecordDate(String fileNameDotExtension, String dateString) {
		setFileNameDotExtentionAndDate(fileNameDotExtension, dateString);
	}

	protected void setFileNameDotExtentionAndDate(String fileNameDotExtension, String dateString) {
		this.file = new File(fileNameDotExtension);
		if (this.file.exists()) {
			if (dateString == null || dateString.trim().isEmpty()) {
				this.fileCreationDate = formater.format(new Date(file
						.lastModified()));
				log.info("no date specified, using file's last modification date: "
						+ this.fileCreationDate);
			} else {
				// FIXME check date format?
				this.fileCreationDate = dateString;
			}		
			this.fileExtention = getFileExtention(fileNameDotExtension);
			this.fileName = getFileName(fileNameDotExtension);
		} else {
			throw new RuntimeException("can not find file: " + fileNameDotExtension);
		}
	}

	public void run() {
		log.info("found: " + this.file + " using date " + fileCreationDate);
		
			
			if (this.fileName.matches(this.patternMagicLanternRawFileName)) {
				// extract time
				
				// FIXME
				// List<String> fileNameTokens =
				//   Arrays.asList(this.fileName.split(this.patternTime));
				// log(fileNameTokens);
				final String time = fileName.substring(4, 8);

				// rename it
				String newFileName = fileCreationDate + "_" + time + "_("
						+ fileName + ")";
				log.info("new file name: " + newFileName);
				if (this.file.canWrite()) {
					final String newFileNameDotExtension = newFileName + "."
							+ this.fileExtention;
					final File newFile = new File(newFileNameDotExtension);
					if (newFile.exists()) {
						log.info("file " + newFileNameDotExtension
								+ " already exists! " + "not renaming "
								+ this.fileName + "." + this.fileExtention);
					} else {
						this.file.renameTo(newFile);
					}
				}
			} else {
				log.error("fine name doesn't matches Magic Lantern RAW file pattern (e.g. M03-1835)");
			}
		
	}
	
	protected String getFileName(String fileNameDotExtention) {
		return fileNameDotExtention.substring(0,
				(fileNameDotExtention.length()
						- this.fileExtention.length() - 1 /* 1 = dot.length */));
	}
	
	protected String getFileExtention(String fileNameDotExtention) {
		final String[] token = fileNameDotExtention.split(Pattern.quote("."));
		return token[token.length-1];
	}

	public static void main(String[] args) {
		
		try {
		List<String> commandLineArguments = Arrays.asList(args);
		if (commandLineArguments == null || commandLineArguments.size() < 1) {
			log.error("Please specify a file name.");
		} else {
			Iterator<String> i = commandLineArguments.iterator();
			String fileNameDotExtension = i.next();

			String dateAsString = "";
			if (i.hasNext()) {
				dateAsString = i.next();
			}
			// execute in this thread
			new RawWithRecordDate(fileNameDotExtension, dateAsString).run();

			// execute in a separate thread
			// new Thread(new RawWithRecordDate(fileNameDotExtension)).start();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}