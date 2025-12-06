# ChocAn System - Build Documentation

This document provides detailed instructions for building, compiling, and running the ChocAn Terminal System on Windows, Linux, and macOS.

NOTE: This was written with the assist of Cursor AI

---

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Project Structure](#project-structure)
3. [Building on Windows](#building-on-windows)
4. [Building on Linux](#building-on-linux)
5. [Building on macOS](#building-on-macos)
6. [Running the Application](#running-the-application)
7. [Troubleshooting](#troubleshooting)

---

## Prerequisites

### Java Development Kit (JDK)

This application requires **Java 11 or higher**. You need the JDK (not just JRE) to compile the source code.

#### Verify Java Installation

Open a terminal or command prompt and run:

```bash
java -version
javac -version
```

Both commands should return version information. If not, install the JDK for your platform.

#### Installing Java

| Platform | Installation Method |
|----------|---------------------|
| **Windows** | Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [Adoptium](https://adoptium.net/) |
| **Linux (Ubuntu/Debian)** | `sudo apt update && sudo apt install default-jdk` |
| **Linux (Fedora/RHEL)** | `sudo dnf install java-17-openjdk-devel` |
| **Linux (Arch)** | `sudo pacman -S jdk-openjdk` |
| **macOS** | `brew install openjdk@17` or download from [Oracle](https://www.oracle.com/java/technologies/downloads/) |

---

## Project Structure

```
ChocAn/
├── src/
│   └── chocan/           # Java source files
│       ├── Main.java     # Application entry point
│       ├── Terminal.java # Main GUI class
│       └── ...           # Other source files
├── bin/                  # Compiled .class files (output)
├── lib/
│   └── gson-2.10.1.jar   # Google Gson library (JSON parsing)
├── database/             # JSON data files
│   ├── members.json
│   ├── providers.json
│   ├── managers.json
│   ├── operators.json
│   ├── servicerecords.json
│   └── suspendedmembers.json
├── reports/              # Generated reports output
├── BUILD.md              # This file
└── README.md             # Project overview
```

---

## Building on Windows

### Using Command Prompt

1. **Open Command Prompt** and navigate to the project directory:
   ```cmd
   cd C:\path\to\ChocAn
   ```

2. **Create the output directory** (if it doesn't exist):
   ```cmd
   if not exist bin mkdir bin
   ```

3. **Compile all Java source files**:
   ```cmd
   javac -d bin -cp "lib\gson-2.10.1.jar" src\chocan\*.java
   ```

4. **Run the application**:
   ```cmd
   java -cp "bin;lib\gson-2.10.1.jar" chocan.Main
   ```

### Using PowerShell

1. **Navigate to the project directory**:
   ```powershell
   cd C:\path\to\ChocAn
   ```

2. **Create the output directory**:
   ```powershell
   New-Item -ItemType Directory -Force -Path bin
   ```

3. **Compile all Java source files**:
   ```powershell
   javac -d bin -cp "lib\gson-2.10.1.jar" src\chocan\*.java
   ```

4. **Run the application**:
   ```powershell
   java -cp "bin;lib\gson-2.10.1.jar" chocan.Main
   ```

### Windows Batch Script

Create a file named `build.bat` in the project root:

```batch
@echo off
echo === ChocAn Build Script ===
echo.

REM Create bin directory if it doesn't exist
if not exist bin mkdir bin

REM Compile Java source files
echo Compiling Java source files...
javac -d bin -cp "lib\gson-2.10.1.jar" src\chocan\*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Build successful!
    echo Run with: java -cp "bin;lib\gson-2.10.1.jar" chocan.Main
) else (
    echo.
    echo Build failed!
    exit /b 1
)
```

Create a file named `run.bat` in the project root:

```batch
@echo off
java -cp "bin;lib\gson-2.10.1.jar" chocan.Main
```

---

## Building on Linux

### Using Terminal

1. **Navigate to the project directory**:
   ```bash
   cd /path/to/ChocAn
   ```

2. **Create the output directory** (if it doesn't exist):
   ```bash
   mkdir -p bin
   ```

3. **Compile all Java source files**:
   ```bash
   javac -cp lib/gson-2.10.1.jar -d bin src/chocan/*.java && echo "Compilation successful!"
   ```

4. **Run the application**:
   ```bash
   export DISPLAY=:0 && java -cp "bin:lib/gson-2.10.1.jar" chocan.Main
   ```

> **Note**: Linux uses `:` (colon) as the classpath separator, not `;` (semicolon).

### Linux Shell Script

Create a file named `build.sh` in the project root:

```bash
#!/bin/bash

echo "=== ChocAn Build Script ==="
echo

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile Java source files
echo "Compiling Java source files..."
javac -d bin -cp "lib/gson-2.10.1.jar" src/chocan/*.java

if [ $? -eq 0 ]; then
    echo
    echo "Build successful!"
    echo "Run with: ./run.sh"
else
    echo
    echo "Build failed!"
    exit 1
fi
```

Create a file named `run.sh` in the project root:

```bash
#!/bin/bash
java -cp "bin:lib/gson-2.10.1.jar" chocan.Main
```

Make the scripts executable:

```bash
chmod +x build.sh run.sh
```

---

## Building on macOS

### Using Terminal

macOS build instructions are identical to Linux:

1. **Navigate to the project directory**:
   ```bash
   cd /path/to/ChocAn
   ```

2. **Create the output directory**:
   ```bash
   mkdir -p bin
   ```

3. **Compile all Java source files**:
   ```bash
   javac -d bin -cp "lib/gson-2.10.1.jar" src/chocan/*.java
   ```

4. **Run the application**:
   ```bash
   java -cp "bin:lib/gson-2.10.1.jar" chocan.Main
   ```

> **Note**: macOS uses `:` (colon) as the classpath separator, same as Linux.

### macOS Shell Scripts

Use the same shell scripts as Linux (`build.sh` and `run.sh`). Make them executable:

```bash
chmod +x build.sh run.sh
```

### Homebrew Java Setup (if needed)

If you installed Java via Homebrew and the `java` command isn't found:

```bash
# For OpenJDK 17
export JAVA_HOME="/opt/homebrew/opt/openjdk@17"
export PATH="$JAVA_HOME/bin:$PATH"
```

Add these lines to your `~/.zshrc` or `~/.bash_profile` to make them permanent.

---

## Running the Application

### Quick Reference

| Platform | Command |
|----------|---------|
| **Windows** | `java -cp "bin;lib\gson-2.10.1.jar" chocan.Main` |
| **Linux** | `java -cp "bin:lib/gson-2.10.1.jar" chocan.Main` |
| **macOS** | `java -cp "bin:lib/gson-2.10.1.jar" chocan.Main` |

### Application Overview

The ChocAn Terminal System is a **Java Swing GUI application**. When launched:

1. A login window will appear
2. Enter your credentials (name, user number, and PIN)
3. Based on your role (Member, Provider, Manager, or Operator), you'll see the appropriate interface

### Test Credentials

Check the `database/` folder JSON files for existing user credentials to test the application.

---

## Troubleshooting

### Common Issues

#### 1. `'javac' is not recognized` (Windows) or `command not found: javac`

**Cause**: Java JDK is not installed or not in your PATH.

**Solution**:
- Install the JDK (see [Prerequisites](#prerequisites))
- Add Java to your PATH:
  - **Windows**: Add `C:\Program Files\Java\jdk-XX\bin` to your System PATH
  - **Linux/macOS**: Add `export PATH="$JAVA_HOME/bin:$PATH"` to your shell profile

#### 2. `Error: Could not find or load main class chocan.Main`

**Cause**: Classpath is incorrect or compilation failed.

**Solution**:
- Ensure you're running the command from the project root directory
- Verify the `bin/chocan/Main.class` file exists
- Check that you're using the correct classpath separator (`;` for Windows, `:` for Linux/macOS)

#### 3. `error: package com.google.gson does not exist`

**Cause**: The Gson library is not in the classpath during compilation.

**Solution**:
- Verify `lib/gson-2.10.1.jar` exists
- Ensure the `-cp` flag includes the Gson JAR file

#### 4. GUI doesn't appear (Linux)

**Cause**: No display server available (common in headless environments or WSL).

**Solution**:
- For WSL, install an X server like VcXsrv or use WSLg
- Set the `DISPLAY` variable: `export DISPLAY=:0`
- For headless servers, use X virtual framebuffer (Xvfb)

#### 5. JSON parsing errors on startup

**Cause**: Corrupted or malformed JSON files in the `database/` directory.

**Solution**:
- Check JSON files for syntax errors
- Restore from backup or recreate with valid JSON structure

### Clean Build

To perform a clean build, delete the `bin/` directory and recompile:

**Windows**:
```cmd
rmdir /s /q bin
mkdir bin
javac -d bin -cp "lib\gson-2.10.1.jar" src\chocan\*.java
```

**Linux/macOS**:
```bash
rm -rf bin
mkdir -p bin
javac -d bin -cp "lib/gson-2.10.1.jar" src/chocan/*.java
```

### Verbose Compilation

For debugging compilation issues, use the `-verbose` flag:

```bash
javac -verbose -d bin -cp "lib/gson-2.10.1.jar" src/chocan/*.java
```

---

## IDE Setup (Optional)

### IntelliJ IDEA

1. Open IntelliJ IDEA → **File** → **Open** → Select the ChocAn folder
2. Right-click `src` folder → **Mark Directory as** → **Sources Root**
3. Right-click `lib/gson-2.10.1.jar` → **Add as Library**
4. Right-click `Main.java` → **Run 'Main.main()'**

### Eclipse

1. **File** → **Import** → **General** → **Existing Projects into Workspace**
2. Select the ChocAn folder
3. Right-click project → **Build Path** → **Configure Build Path**
4. **Libraries** tab → **Add External JARs** → Select `lib/gson-2.10.1.jar`
5. Run `Main.java`

### VS Code

1. Install the **Extension Pack for Java**
2. Open the ChocAn folder
3. Create `.vscode/settings.json`:
   ```json
   {
       "java.project.sourcePaths": ["src"],
       "java.project.outputPath": "bin",
       "java.project.referencedLibraries": ["lib/**/*.jar"]
   }
   ```
4. Open `Main.java` and click **Run** above the `main` method

---

## Summary

| Task | Windows | Linux/macOS |
|------|---------|-------------|
| **Compile** | `javac -d bin -cp "lib\gson-2.10.1.jar" src\chocan\*.java` | `javac -d bin -cp "lib/gson-2.10.1.jar" src/chocan/*.java` |
| **Run** | `java -cp "bin;lib\gson-2.10.1.jar" chocan.Main` | `export DISPLAY=:0 && java -cp "bin:lib/gson-2.10.1.jar" chocan.Main` |
| **Path Separator** | `\` (backslash) | `/` (forward slash) |
| **Classpath Separator** | `;` (semicolon) | `:` (colon) |

