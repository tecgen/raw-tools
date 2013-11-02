package de.tecgen.raw.tools.dng;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Purpose of this class is to allow basic operations for Cinema DNG clips. 
 * <ul>
 *  <li>renaming of clips is equal to renaming simply special named directories with DNG-files following a special naming pattern</li>
 * </ul>
 *  
 * 
 * @author Marco Pehla
 * @date 2013-10-27
 */
public class CinemaDNG {
	
	private final static String ExtentionCinemaDNG = "dng";
	public final String FRAME_INDEX_SEPARATOR = "_";
	private final String REGEX_REMOVE_ALL_LEADING_ZEROS = "(?<!\\d)0+(?=\\d+)";

	/**
	 * 
	 * @param clip - a Cinema DNG clip (a directory containing DNG image files)
	 * @throws FileNotFoundException
	 */
	public static void rename(File clip) throws FileNotFoundException {
		if(isCinemaDNGClip(clip)) {
			// iterate over all files and rename them 
			// (use a thread pool to execute renaming of the DNG-file in parallel 
			// if that would speed up the whole process on e.g. on SSDs)
			
		} else {
			throw new FileNotFoundException("Given file is not a valid CinemaDNG clip.");
		}
	}
	
	/**
	 * @param fileName of one file in a CinemaDNG clip folder e.g. 2013-10-27_1812_(M27-1812)_C0000_00001
	 * @return the frame index as integer, in the example above its 1
	 * @throws IllegalArgumentException in case the frame index can not be extracted from the given file name
	 */
	protected int getFrameIndexByFileName(String fileName) throws IllegalArgumentException {
		
		StringTokenizer tokenizer = new StringTokenizer(fileName, FRAME_INDEX_SEPARATOR);
		String lastElement = "";
		// iterate over all elements until the last element has been found
		while(tokenizer.hasMoreElements()) {
			lastElement = tokenizer.nextToken();
		}
		// remove all leading zeros
		lastElement = lastElement.replaceAll(REGEX_REMOVE_ALL_LEADING_ZEROS, "");
		
		try {
			return Integer.valueOf(lastElement).intValue();
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Unable to parse frame index from given file name \"" + fileName + "\" .");
		}
	}
	
	/**
	 * 
	 * @param clip 
	 * @return true when the given clip is a valid CinemaDNG clip
	 */
	private static boolean isCinemaDNGClip(File clip) {
		if(clip.isDirectory()) {
			final String clipDirectoryName = clip.getName();
			
			// get all *.dng files in the directory
			List<File> dngFiles = Arrays.asList(clip.listFiles(new FilenameFilter() {
				public boolean accept(File directory, String fileName) {
					return fileName.endsWith("."+ExtentionCinemaDNG.toLowerCase()) 
							|| fileName.endsWith("."+ExtentionCinemaDNG.toUpperCase());
				}	
			}));
			
			// the directory contains at least one DNG-file ...
			// e.g. "2013-10-27_1812_(M27-1812)_C0000_00000.dng"
			if(dngFiles.size() < 1) {
				// stop when no matching DNG-file has been found
				return false;
			} else {
				// ... with suffix _00000 
				int counter = 0;
				for(File dngFile : dngFiles) {
					// all DNG-files need to have the same file name (except for the suffix)
					// and moreover the directory shares this name too!
					
				}
				
				
				
				
			}
			
			
			
			
			
			
			//FIXME 
			return false;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
