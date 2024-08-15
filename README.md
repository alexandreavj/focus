# focus

## what is _focus_?

*focus* is a desktop application that helps you boost your concentration and performance. Making use of the Pomodoro Technique, *focus* allows you to set a timer for your work sessions and breaks, maximizing your productivity. In addition, *focus* lets you customize your experience by changing the background of your workspace, enabling you to create a more comfortable environment for your work.
It was developed as a personal project in order to create my own study tool, customized to my needs and preferences.

*focus* is built using JavaFX, a software for creation of desktop applications using Java.

## features
- **Pomodoro Timer**: set a timer for your work sessions and breaks
- **Customizable Timer**: set the duration of your work sessions and breaks
- **Customizable Background**: change the background of your workspace
- **Volume Control**: mute your background music when needed

## how to install?
In this tutorial, you will be guided through the installation process of *focus*. There will be three methods to install the application: using the pre-built JAR file, using the source code and IntelliJ IDEA, and using the source code and command line. This is because there might be compatibility problems with the pre-built JAR file. Nonetheless, the pre-built JAR file is the recommended method for installation, since it is the most straightforward process.


### using pre-built JAR file
#### before starting
1. Make sure you have JDK 22 or higher installed in your system, and if it is recognized by your system (you can check this by running `java -version` in your terminal).
2. Download the pre-built JAR file from the [releases page](https://github.com/alexandreavj/focus/releases) of this repository.

#### Windows
1. Open 'Notepad' and paste the following code, replacing `path\to\focus.jar` with the path to the downloaded JAR file:
```batch
java -jar path\to\focus.jar
```
2. Save the file as `focus.bat`.
3. To create a shortcut for the application (which you can add to your taskbar or Start Menu), right-click on a directory and select `New` > `Shortcut`. In the location field, paste the path to the `focus.bat` file. Click `Next`, name the shortcut `focus`, and click `Finish`. To change the icon of the shortcut, right-click on it, select `Properties`, click on `Change Icon`, and select the [_focus_ icon]() from the repository (`ico` format).
4. You can now run the application by double-clicking the shortcut or the `bat` file.

#### macOS
1. Open `Automator` and create a new application.
2. Search for `Get Specified Finder Items` and drag it to the workflow. Select the downloaded JAR file.
3. Search for `Open Finder Items` and drag it to the workflow.
4. Save the application as `focus.app` to `Applications` folder.
5. Navigate to the `Applications` folder and right-click on the `focus.app` file. Select `Get Info` and click on the icon in the top-left corner. Download and open the [_focus_ icon]() from the repository (`png` format) and use `Command` + `A` followed by `Command` + `C` to copy the icon. Click on the icon in the `Get Info` window and use `Command` + `V` to paste the icon.
6You now have _focus_ or your Mac's Launchpad.


### using source code and IntelliJ IDEA to build JAR file
#### before starting
1. Make sure you have JDK 22 or higher installed in your system, and if it is recognized by your system (you can check this by running `java -version` in your terminal).
2. Download and install IntelliJ IDEA.

#### windows and macOS
1. Open IntelliJ IDEA and choose `Get from Version Control`. Paste the URL of this repository and clone it.
2. Open the project and in `Maven` tab, select `Run Anything` and type `mvn clean install` to build the JAR file.
3. The JAR file will be located in the `target` directory of the project, with the name `focus-1.0-SNAPSHOT-shaded.jar`.
4. Follow the instructions in the section [using pre-built JAR file](#using-pre-built-jar-file) to create a shortcut for the application.


### using source code and command line (Windows only)
You must follow this process if you are unable to build the JAR file using IntelliJ IDEA due to a problem with Maven.

#### before starting
1. Make sure you have JDK 22 or higher installed in your system, and if it is recognized by your system (you can check this by running `java -version` in your terminal).

#### windows
1. Go to [Maven's official website]() and follow the instructions to download and install Maven.
2. Open the command line and navigate to the directory where the project was cloned.
3. Run the following command to build the JAR file: `mvn clean install`.
4. The JAR file will be located in the `target` directory of the project, with the name `focus-1.0-SNAPSHOT-shaded.jar`.
5. Follow the instructions in the section [using pre-built JAR file](#using-pre-built-jar-file) to create a shortcut for the application.


#### _by alex j._

## assets
- [play icon](https://www.flaticon.com/free-icon/play_9581128?term=start&page=1&position=8&origin=search&related_id=9581128)
- [pause icon](https://www.flaticon.com/free-icon/pause_151859?term=pause&page=1&position=4&origin=search&related_id=151859)
- [reset icon](https://www.flaticon.com/free-icon/reset_2618245?term=reset&page=1&position=1&origin=search&related_id=2618245)
- [settings icon](https://www.flaticon.com/free-icon/settings_992668?term=settings&page=1&position=14&origin=search&related_id=992668)
- [save icon](https://www.flaticon.com/free-icon/diskette_2874050?term=save&page=1&position=11&origin=search&related_id=2874050)
- [upload icon](https://www.flaticon.com/free-icon/upload-big-arrow_81081?term=upload&page=1&position=10&origin=search&related_id=81081)
- [volume icon](https://www.flaticon.com/free-icon/volume_7640163?term=sound&page=1&position=5&origin=search&related_id=7640163)
- [mute icon](https://www.flaticon.com/free-icon/volume_7640162?related_id=7640162)
- [base image for built-in background](https://pbs.twimg.com/media/EWKDp_rWAAotzZ1.jpg:large)
