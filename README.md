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
The following tutorial applies for Windows, Linux and macOS users. For simplicity, this tutorial will take advantage of IntelliJ IDEA's integrations and automatization of some installations.
In alternative, you can try one of the pre-built JAR files in the `Releases` section of this repository (you will still need JDK 22 or later on your system).

### prerequisites
- IntelliJ IDEA already installed in the system
- Most recent version of JDK installed (JDK 22 or later)
  1. Create a new project in IntelliJ IDEA
  2. In 'JDK' drop-down menu, download the most recent version of JDK

### steps
1. Clone the repository to your local machine
2. Open the project in IntelliJ IDEA
3. Double-click on 'Control' key to open 'Run anything' dialog
4. Run the command `mvn clean install` to build the project
5. A new folder named 'target' will be created in the project directory. In that directory, a JAR file with name ending in `-shaded.jar` will be created. This is the executable JAR file of the *focus* desktop app.

### additional steps (_macOS users_)
To have *focus* on your Mac's Launchpad, follow these steps:
1. Open `Automator`
2. Create a new `Application`
3. Add `Get Specified Finder Items` action, and select the JAR file
4. Add `Open Finder Items` action after the previous one
5. Save the workflow (with the name *focus* ;)) and move it to the `Applications` folder

### troubleshooting
If there is an error in the build process related to JavaFX Media (or other dependency) not being found, try the following:
- `Reload All Maven Projects` on Maven tab in IntelliJ IDEA
- Try to `Download Sources and\or Documentation` in the Maven tab
- Add the dependencies manually to the project:
  1. In IntelliJ IDEA, go to `File` -> `Project Structure` -> `Libraries`
  2. Click on the `+` sign and select `Java`
  3. According to the location of the JavaFX SDK in your system (which you can check in the libraries listed in `Project Structure`), add JavaFX Media JAR file.
- Try to change the versions of the dependencies in the `pom.xml` file according to IDE's suggestions (or try to remove the version tag)

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
