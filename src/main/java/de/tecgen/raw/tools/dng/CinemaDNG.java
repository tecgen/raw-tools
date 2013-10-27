package de.tecgen.raw.tools.dng;

import java.io.File;
import java.io.FileNotFoundException;

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

	/**
	 * 
	 * @param clip - a Cinema DNG clip (a directory containing DNG image files)
	 * @throws FileNotFoundException
	 */
	public static void rename(File clip) throws FileNotFoundException {
		if(isCinemaDNGClip(clip)) {
			// iterate over all files and rename them 
			// (use a thread pool to execute renaming of the DNG-file in parallel)
		} else {
			throw new FileNotFoundException("Given file is not a valid CinemaDNG clip.");
		}
	}
	
	/**
	 * 
	 * @param clip 
	 * @return true when the given clip is a valid CinemaDNG clip
	 */
	private static boolean isCinemaDNGClip(File clip) {
		//FIXME 
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
