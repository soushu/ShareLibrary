# ShareLibrary
![Alt text](./image1.png)

## Description
Add Share menu to Android Actionbar.
![Alt text](./image2.png)

## Language
- Android: Java

## Requirements
androidX

## Getting Started
- Add it in your root build.gradle at the end of repositories.
![Alt text](./image3.png)

- Add the dependency.
![Alt text](./image4.png)

- Extends ShareMenuActivity.
![Alt text](./image5.png)

- Set webView with setWebView()
![Alt text](./image6.png)

- If necessary, set the title of the bar with setTitle()
![Alt text](./image7.png)

- If you want to share not only the URL but also the app information, set the app name with setAppName() to add a link to the PlayStore.
![Alt text](./image8.png)

- If you want the icon color to be white, use setColor(). The default is black.
![Alt text](./image9.png)

- If you want to add a menu separately, you can add it with onCreateOptionsMenu with menu add.
![Alt text](./image10.png)
