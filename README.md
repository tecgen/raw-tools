# RAW Tools

RAW Tools are a set of Java classes and command line scripts 
which help me to handle Magic Lanterns RAW Video files or CinemaDNG clips better. They are mostly written in Java in order to be platform 
independent and can be used on OSX and Linux. 

#How to build?
* use Maven 3 to build the Java project (**mvn clean install**)
* all bash shell scripts are at least compatible with OSX 10.8.4, but its very likely that they are working on Linux as well
* user dependent configuration for the shell scripts is stored in **user.cfg**

# Supported use-cases 

## Copy all RAW and spanned files 

A simple shell script copies all your *.RAW, *.R01, ..., *.R99 files from you Compact Flash or SD-Card to you local folder.

**./copyRawFilesFromCard.sh**

Afterwards you can process them further with the following use-cases.

## Add Date and Time to all RAW files

Because of the file system restrictions, Magic Lanterns RAW file names are only 8 chars long e.g. **M04-1823.RAW**. With help of
this use-case you can add to all RAW (and spanned) files the date and time, when these files have been recorded.

**./convertAllRawFiles.sh M04-1823.RAW** 

    12 [main] INFO de.tecgen.raw.tools.filename.RawWithRecordDate - no date specified, using file's last modification date: 2013-11-02
    12 [main] INFO de.tecgen.raw.tools.filename.RawWithRecordDate - found: M04-1823.RAW using date 2013-11-02
    13 [main] INFO de.tecgen.raw.tools.filename.RawWithRecordDate - new file name: 2013-11-02_1823_(M04-1823)


## Renaming of CinemaDNG clips

With help of this shell script & Java class you are able to rename existing CinemaDNG clips, which
are directories including DNGs files. When they are named in a proper way e.g. Davinci Resolve Lite shows the 
animated clips when you are in the parent folder. It useful when you want to archive your clips, order them by date
and want to give them a meaningful name.

**./renameCinemaDngClip.sh M27-1812/ 2013-10-27_1812_Cats_C0000/**





