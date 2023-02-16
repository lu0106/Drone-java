This is an agricultural drone dashboard software that is programmed using Java 8 SE (JDK).  (It requires JavaFX.)

The repository contains multiple directories:
* `jdrone` -- Submodule of TA code for drone from git@gitlab.cs.uab.edu:jesusaur/jdrone.git 
* `src/`  -- This is the source code.
* `src/img/`  -- These are images used in the project.  (ATTRIBUTION.md tells where the image files come from.)
* `src/test/` -- These are [JUnit 5](https://junit.org/junit5/) unit tests.
* `.gitignore` -- this file lists files and directories that git should ignore (`bin/`)


Note: the project's [icon](https://www.flaticon.com/premium-icon/camera-drone_3211402) was made by [Freepik](https://www.freepik.com) and comes from [www.flaticon.com](https://www.flaticon.com/).  It is provied under the FlatIcon License, which states "Free for personal and commercial purpose with attribution."  [Legal](https://www.freepikcompany.com/legal#nav-flaticon).


## Developer
Welcome to the development team.  We will walk you through a few steps to get you up an running and developing.

### Software Requirements
* Java 8 (and install JavaFX)
* SceneBuilder
* Eclipse

### Instructions for Getting Started Developing

* Step 1. **Run git clone**:

```bash
git clone git@gitlab.cs.uab.edu:mdcochra/520-GTeam2-Impl.git
  OR
git clone git@gitlab.cs.uab.edu:mdcochra/520-GTeam2-Impl.git another-folder-name
```
It is probably best to do this in a folder under the eclipse-workspace folder.  The default folder name is "520-GTeam2-Impl".  If you want to use another folder name (`another-folder-name` in above) just put that name it at the end of the git clone line, or rename the folder after cloning.

* Step 2. **Download the submodules** ----> Change directory into the `jdrone/` subfolder.  Then run

```
$ git submodule update --init --recursive
```
It will give you the message like

```
Cloning into /example/folder/520-GTeam2-Impl/jdrone
```

When done (and was successful) it should tell you:

```
Submodule path './': checked out 'f459064e4ccaf1c79a49b53116cd8da3ecdb23cc'
```

f459064e... This is the SHA value of the commit of jdrone that this project is pegged to. 

**Note:** This is a large download (2 GiB) and it may take a while to download. 

* Step 3. **Verify** ---> Verify that it downloaded files into the jdrone folder.  Good.

```
[micahc@micah-virtualbox another-folder-name]$ ls
jdrone  README.md  src
```

* Step 4. **Import the folder into Eclipse** ---> Start Eclipse.  On the File menu > "Open Projects from File System..."  Click the "Directory..." button.  Navigate the file tree to find the folder name, highlight the name, and click the "Open" button.  It should identify that there are folders for this project and the jdron project. Unclick the jdrone/ folder.  Click Finish.

* Step 5. It should import the project in the Package Explorer.

* Step 6. **Add the JAR** (skip this step unless if you are having problems.) --->  Right click on the project folder "520-GTeam2-Impl" and select "Properties".  Select "Java Build Path, select the "Libraries" tab

* Step 7. **Run the project.** -->  It will pop up a window asking to "Select Java Application".  Select "MainApp - farm".  That's it the project should run.

### Alternative to git submodule
If you want to skip step 2 and already have the drone *.jar files.  You can copy those files into place. 

1. Change directory into the jdrone folder.  You'll have to make a couple folders. Use the command `mkdir -p jdrone/target`
2. Copy the `.jar` files into that target folder.
3. Go to step 4  of the above instructions (Import the folder into Eclipse).