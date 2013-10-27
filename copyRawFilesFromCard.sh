#!/bin/bash
source user.cfg
rsync --progress $source_folder_raw/*.R* .


#rsync --progress /Volumes/EOS_DIGITAL/DCIM/100CANON/*.R* .

# alternative without progress bar: 
# cp /Volumes/EOS_DIGITAL/DCIM/100CANON/*.RAW .
