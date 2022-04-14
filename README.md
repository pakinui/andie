# Team Marginal | ANDIE - Group Project

### Team Members
- Christopher Mairs
- Jamie Rule
- Mahfuz Bin Adbul Razak
- Poppy Schlaadt
- Pippi Priestly King
### Features
 - [Sharpen filter](#sharpen-filter)
 - [Gaussian Blur filter](#gaussian-blur-filter)
 - [Median filter](#median-filter)
 - [Brightness and Contrast adjustment](#brightness-and-contrast-adjustment)
 - [Image resize](#image-resize)
 - [Image rotations](#image-rotations)
 - [Image flip](#image-flip)
 - [Image export](#image-export)
 - [Exception handling](#exception-handling)
 - [Toolbar for common operations](#toolbar-for-common-operations)
 - [Keyboard shortcuts](#keyboard-shortcuts)
 - [General Features](#general-features)

---

## Sharpen filter
### Contributors:
- Christopher Mairs
### Accessible via:
- Filter Menu (Sharpen Filter)
- Keyboard shortcut: `Ctrl`(Windows) / `COMMAND`(macOS) + `P`
### Testing
No formal testing framework was used on the Sharpen Filter. However I tested it with multiple kinds of image types such as JPEG & PNG. I also tested it with images of different widths and heights, and had no errors with any of them.

### Issues:
- Currently there are no known issues with the Sharpen Filter.

---
## Gaussian Blur filter
### Contributors:
- Poppy Schlaadt
- Christopher Mairs
- Pippi Priestly King
### Accessible via:
- Filter Menu (Gussian Filter)
- Keyboard shortcut: `Ctrl`(Windows) / `COMMAND`(macOS) + `G`
### Testing
No formal testing framework was used on the Gaussian FIlter. However, I have tested multiple images, multiple times. I tested JPEG, JPG and PNG, these three images all had different heights and widths and they all correctly worked.

### Issues:
- An issue we have with the gaussian filter is a black border that occurs around the image. While this is okay for this project, if we had more time we would hopefully be able to remove the black boarder.
---
## Median filter
### Contributors:
- Poppy Schlaadt
### Accessible via:
- Filter menu (Median Filter)
- Keyboard shortcut: `Ctrl`(Windows) / `COMMAND`(macOS) + `N`
### Testing
There was no formal testing framework used but I tested on both greyscale and coloured images, as well as images that had already been altered with another filter (i.e., Mean Filter) and the filter behaved as expected. I tested on images with different size variations. For example, narrow and tall images and short and wide images. The filter worked on all kinds of sizes and there were no black borders, which showed that the pixel loop was working correctly.

### Issues:
- The only issue found is that it takes a bit of time for the filter to be applied to larger images.
---
## Brightness and Contrast adjustment
### Contributors:
- Poppy Schlaadt
### Accessible via:
- Colour Menu (Brightness and Contrast)
- Keyboard shortcut: `Ctrl`(Windows) / `COMMAND`(macOS) + `B`
### Testing
There was no formal testing framework used, but tests were carried out with images with different colour values, for example, greyscale and colour images. Tests were run on every possible combination of brightness and contrast the user could pick and all images changed as expected. Tests were run to make sure that if the user closed the pop up window the changes to brightness and contrast they selected would not be applied, and that the changes were only applied if the ‘done’ button was selected. It also works as expected with an image that has had previous changes made to it.

### Issues:
- No known issues.
---
## Image resize
### Contributors:
- Pippi Priestly King
### Accessible via:
- Transform Menu (Scale)
### Testing
Tested on different shaped images (ie. square/rectangular) to make sure scaling was correct. JSlider method means user cannot enter invalid values for scale. Can scale to be smaller and larger to do any scaling the user might want to do. Clearly using a % value so users understand how to scale/how much the image will be scaled by. 

### Issues:
- No known issues.
---
## Image rotations
### Contributors:
- Jamie Rule
### Accessible via:
- Transform menu(Rotate)
- Keyboard shortcut ‘Ctrl’(Windows) / ‘COMMAND’(macOS) + ‘R’ for the rotate pop-up window
- ToolBar contains rotate left and rotate right buttons
### Testing
Testing on images to see if the rotations went the right way and did not mess up any pixel. 180 degree works for square and rectangle images but 90 degree rotates did not work for rectangle images.

### Issues:
- 90 degree rotates at this time still do not work for rectangle images
---
## Image flip
### Contributors:
- Poppy Schlaadt
- Christopher Mairs
- Pippi Priestly King
### Accessible via:
- Transform menu (Flip) for the flip pop-up window
- Keyboard shortcut: `Ctrl`(Windows) / `COMMAND`(macOS) + `F` for the pop-up window
- Toolbar contains a horizontal flip button and a vertical flip button
### Testing
There was no formal testing framework used, but tests were run on images with a variety of ratios(tall and narrow, short and wide, etc.) to ensure that the iterating and exchanging of pixels behaved like expected. Tests were also carried out with how the user can interact with the pop-up window to ensure that if no flip button had been selected and the window was closed or if the button was selected, the image would not change. Tests were run to ensure that the user was allowed to flip the image in both directions(for the same image) and, as many times as they wanted.

### Issues:
- No known issues.
---
## Image export
### Contributors:
- Poppy Schlaadt
### Accessible via:
- File menu (Export image)
- Keyboard shortcut: `Ctrl`(Windows) / `COMMAND`(macOS) + `E`
### Testing
No formal testing framework used, but tests to ensure that if the user typed a filename without a file format (i.e. ‘.png’) then the image would automatically be saved as a ‘.png’. Also tested that users were able to open exported images on their computer as well as, opening a previously exported image and it was still able to be edited in ANDIE.

### Issues:
- No known issues
---
## Exception handling
### Contributors:
- Mahfuz Bin Adbul Razak

### Testing
There was no formal testing framework for exception handling, we worked on exception handling as we found errors throughout testing of other features in the system and solved them throughout the development cycle. Below are a few exceptions that were found and fixed:
- Prevention of incorrect formatted files from being opened by the application
- Preventing the retainment of previous operations when opening a new image
- Preventing Gaussian and Median Filters from being applied when the dialogue box is closed
- Preventing Undo/Redo functions from operating when there nothing to Undo/Redo
- Prevent the use of functions such as undo/redo,flip,rotate or any shortcuts to be performed before an image is opened

### Issues:
- No known issues
---
## Documentation

### Contributors:
- Christopher Mairs
- Jamie Rule
- Mahfuz Bin Adbul Razak
- Poppy Schlaadt
- Pippi Priestly King

We used Javadoc comments with appropriate tags to create useful documentation for anyone else reading/using our code and for all members of the team to understand. We felt this was highly important when working in a group setting and as some elements required more than  one person working on it at a time. These will also be helpful to us in the future and when editing to see what parts of the code do and there is unnecessary code to get rid of. The tags we used were mostly @code and @author and @link to reference other parts of the code and various classes. 


---

## Toolbar for common operations
### Contributors:
- Jamie Rule

### Accessible via:
- The toolbar is always accessible sitting right under the menu bar.
### Testing
Testing involved making sure the jbuttons did the right task and that the icon matched up with the correct button. Also asking people not doing the paper if they could understand what the button did from the icon.

### Issues:
- No known issues
---
## Keyboard Shortcuts
### Contributors:
- Poppy Schlaadt
- Mahfuz Bin Abdul Razak

### Accessible via:
- Any time the ANDIE application is open the keyboard shortcuts are able to be used.
### Testing
The shortcuts use no formal testing framework, however, tests were run to make sure that the shortcuts worked on both Windows and macOS. All shortcuts were tested to ensure all shortcuts had unique keys. Tests were run to make sure only the shortcuts that had been created worked and if random keys were pressed nothing happened.

### Issues:
- An issue is that shortcuts have not been able to be tested on other operating systems(besides Windows and macOS) as we had no access to them. However, I am fairly confident they would work as I used the Java Toolkit Class to make the modifier key used when creating the shortcuts specific to the operating system being run.
---

## General Features
---
### Keyboard Shortcuts
For the shortcuts we wanted only the most used options to be accessible.
These include:
- Image open (`O`)
- Image save (`S`)
- Image export (`E`)
- Undo (`Z`)
- Redo (`Y`)
- Zoom In (`+`)
- Zoom Out (`_`)
- Rotate (`R`)
- Flip (`F`)
We felt that these keyboard shortcuts provided enough flexibility increase the speed and ease that users experience while using the program, without having too many options and causing confusion.

---
### Toolbar
The features on the toolbar include open, save, undo, redo, right rotate, and left rotate. I have chosen these features for the toolbar as they are the most common operations for andie and are very recognisable from their icons.

---
### Exception handling and Exception avoidance
Errors fixed:
- Made only JPG, JPEG and PNG files visible and accessible when in JFileChooser.
- Ensured that all images exported would be saved as either a JPG, JPEG or PNG image file.
- 
Exceptions handled:
- Added try .. catch blocks around any code that was trying to work on an unopened image. (NullPointerException)
- Added a try .. catch block to catch the IndexOutOfBoundsException for when the user tries to rotate an image that is not a square.

---
