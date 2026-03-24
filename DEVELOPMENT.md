# Development Guide

## Getting Started

This guide covers everything needed to set up your development environment and contribute to the Android Attendance System.

---

## Prerequisites

### System Requirements:
- **OS**: Windows, macOS, or Linux
- **RAM**: 8GB minimum (16GB recommended for Android Studio)
- **Storage**: 20GB free space (for SDK, build tools, and dependencies)
- **Internet**: Required for gradle/dependency downloads

### Software:
- Git 2.25+
- Java Development Kit (JDK) 11+ (OpenJDK 17 recommended)
- Android SDK with API 34+
- Android Studio 2023.1+ OR VS Code with Android Tools extension

---

## Environment Setup

### 1. Install Java

#### Windows:
```powershell
# Using winget
winget install Microsoft.OpenJDK.17

# Or download from: https://adoptopenjdk.net/
# Set JAVA_HOME environment variable
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17.0.2"
```

#### macOS:
```bash
# Using Homebrew
brew install openjdk@17
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
```

#### Linux:
```bash
sudo apt-get install openjdk-17-jdk
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

### 2. Install Android SDK

#### Option A: Android Studio (Recommended)
1. Download from https://developer.android.com/studio
2. Run installer and follow setup wizard
3. SDK Manager will install API 34, Build Tools automatically

#### Option B: Command Line Tools
1. Download from https://developer.android.com/studio#command-tools
2. Extract to `~/Android/sdk/`
3. Run: `sdkmanager "platforms;android-34" "build-tools;34.0.0"`

### 3. Set Environment Variables

#### Windows PowerShell:
```powershell
# Permanently set (requires admin)
[Environment]::SetEnvironmentVariable('JAVA_HOME', 'C:\path\to\jdk-17', 'User')
[Environment]::SetEnvironmentVariable('ANDROID_HOME', 'C:\Users\YourName\AppData\Local\Android\sdk', 'User')

# Verify
$env:JAVA_HOME
$env:ANDROID_HOME
```

#### macOS/Linux (add to ~/.bashrc or ~/.zshrc):
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export ANDROID_HOME=~/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools
```

### 4. Clone Repository

```bash
git clone https://github.com/MrrAmissah/Android-Attendance-Appr.git
cd Android-Attendance-Appr
```

---

## Project Structure Overview

```
Android-Attendance-Appr/
├── app/
│   ├── src/main/
│   │   ├── java/com/android/attendance/
│   │   │   ├── activity/        # 14 Activities
│   │   │   ├── bean/            # Data models
│   │   │   ├── db/              # Database code
│   │   │   └── context/         # App context
│   │   ├── res/                 # Resources
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── gradle/
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md
```

---

## Building the Project

### Using Gradle (Command Line)

```bash
# Set Java environment (Windows)
$env:JAVA_HOME = "C:\path\to\jdk-17.0.2"

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Build and install on connected device
./gradlew installDebug

# Run specific task
./gradlew assembleDebug --info

# Clean build
./gradlew clean assembleDebug

# Check gradle version
./gradlew --version
```

### Using Android Studio

1. **Open Project**:
   - File → Open → Select project folder
   - Wait for Gradle sync (bottom right progress)

2. **Build**:
   - Build → Make Project (Ctrl+F9)
   - Or Select Run Configuration → Run (Shift+F10)

3. **Run on Device/Emulator**:
   - Connect device or create emulator
   - Select from device dropdown
   - Click Run button (▶️)

### Using VS Code

1. **Install Extension**:
   - Android Tools (vscode-android-tools)

2. **Run Build Task**:
   - Terminal → Run Task
   - Select `gradle-build`
   - View output in terminal

---

## Working with Activities

### Adding a New Activity

1. **Create Java File**:
```java
// app/src/main/java/com/android/attendance/activity/MyActivity.java
package com.android.attendance.activity;

import android.app.Activity;
import android.os.Bundle;

public class MyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }
}
```

2. **Create Layout File**:
```xml
<!-- app/src/main/res/layout/activity_my.xml -->
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <!-- Your UI elements -->
</LinearLayout>
```

3. **Register in AndroidManifest.xml**:
```xml
<activity
    android:name="com.android.attendance.activity.MyActivity"
    android:exported="false"
    android:label="@string/app_name" />
```

### Activity Lifecycle
```java
onCreate()      // Called when Activity is created
    ↓
onStart()       // Called when Activity becomes visible
    ↓
onResume()      // Called when Activity gains focus
    ↓
onPause()       // Called when Activity loses focus
    ↓
onStop()        // Called when Activity is no longer visible
    ↓
onDestroy()     // Called when Activity is destroyed
```

---

## Working with Database

### Database Operations

#### Create Students Table:
```java
// In DatabaseHelper.onCreate()
db.execSQL("CREATE TABLE IF NOT EXISTS STUDENTS (" +
    "roll_no TEXT PRIMARY KEY, " +
    "name TEXT NOT NULL, " +
    "branch TEXT, " +
    "year INTEGER, " +
    "email TEXT)");
```

#### Insert Record:
```java
StudentDAO dao = new StudentDAO(context);
Student student = new Student("S001", "John Doe", "CSE", 1);
dao.createStudent(student);
```

#### Query Records:
```java
StudentDAO dao = new StudentDAO(context);
List<Student> students = dao.getAllStudents();
```

#### Update Record:
```java
StudentDAO dao = new StudentDAO(context);
student.setEmail("new@email.com");
dao.updateStudent(student);
```

#### Delete Record:
```java
StudentDAO dao = new StudentDAO(context);
dao.deleteStudent("S001");
```

---

## Testing

### Unit Testing

Create test files in `app/src/test/java/`:

```java
import junit.framework.TestCase;

public class StudentTest extends TestCase {
    private Student student;
    
    public void setUp() {
        student = new Student("S001", "Test Student", "CSE", 1);
    }
    
    public void testStudentCreation() {
        assertEquals("S001", student.getRollNo());
        assertEquals("Test Student", student.getName());
    }
}
```

Run tests:
```bash
./gradlew test
```

### Instrumentation Testing

Create in `app/src/androidTest/java/`:

```java
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> rule = 
        new ActivityTestRule<>(LoginActivity.class);
    
    @Test
    public void testLoginSuccess() {
        // Test login functionality
    }
}
```

Run tests:
```bash
./gradlew connectedAndroidTest
```

---

## Debugging

### Logcat (Android Logs)

```bash
# View all logs
adb logcat

# Filter by app
adb logcat | grep com.android.attendance

# Save to file
adb logcat > logs.txt

# Clear logs
adb logcat -c
```

### In Code:
```java
import android.util.Log;

Log.d("TAG", "Debug message");
Log.i("TAG", "Info message");
Log.w("TAG", "Warning message");
Log.e("TAG", "Error message");
```

### Android Studio Debugger
1. Set breakpoint (click line number)
2. Debug → Debug 'app'
3. Step through code
4. Inspect variables

---

## Code Style Guidelines

### Naming Conventions

```java
// Classes: PascalCase
public class StudentActivity { }

// Methods: camelCase
public void markAttendance() { }

// Constants: UPPER_SNAKE_CASE
private static final int MAX_STUDENTS = 100;

// Variables: camelCase
int numberOfStudents;
String studentName;
```

### Code Organization

```java
public class MyActivity extends Activity {
    // Constants
    private static final String TAG = "MyActivity";
    
    // Member variables
    private List<String> items;
    private Button submitBtn;
    
    // Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Implementation
    }
    
    // Public methods
    public void doSomething() {
        // Implementation
    }
    
    // Private methods
    private void helper() {
        // Implementation
    }
}
```

---

## Common Issues & Solutions

### Issue: JAVA_HOME not set
```bash
Error: JAVA_HOME is not set and no 'java' command could be found

# Solution:
$env:JAVA_HOME = "C:\path\to\jdk-17"
```

### Issue: Gradle sync fails
```
Solution:
1. Build → Clean Project
2. File → Sync Now
3. Delete .gradle folder (hidden)
4. Try again
```

### Issue: Build tools not found
```bash
Error: Installed Build Tools revision X.X.X is not available

# Solution:
./gradlew --refresh-dependencies
# Or: Install missing Build Tools in SDK Manager
```

### Issue: Resource compilation error
```
Solution:
1. Check XML files for syntax errors
2. Verify all resource IDs in R.java
3. Clean and rebuild
4. Invalidate cache in Android Studio
```

---

## Git Workflow

### Making Changes

```bash
# Create feature branch
git checkout -b feature/attendance-marking

# Make changes and commit
git add .
git commit -m "Add attendance marking feature"

# Push to repository
git push origin feature/attendance-marking

# Create Pull Request on GitHub
```

### Commit Message Format
```
[TYPE] Brief description

Longer description if needed.
- Point 1
- Point 2

Fixes #123
```

Types: `[FEAT]`, `[FIX]`, `[DOCS]`, `[STYLE]`, `[REFACTOR]`, `[TEST]`

---

## Performance Tips

1. **Minimize Database Queries**: Cache frequently used data
2. **Use Async Operations**: Don't block UI thread
3. **Optimize Images**: Use appropriate resolution/compression
4. **Profile Your Code**: Use Android Profiler
5. **Avoid Memory Leaks**: Unregister listeners properly

---

## Useful Tools & Resources

### Tools:
- Android Studio
- Android SDK Platform Tools
- Gradle
- Git/GitHub
- Android Emulator

### Resources:
- [Android Developers](https://developer.android.com/)
- [Android API Reference](https://developer.android.com/reference)
- [Material Design Guidelines](https://material.io/design)
- [Stack Overflow Android Tag](https://stackoverflow.com/questions/tagged/android)

---

## Troubleshooting Checklist

- [ ] Java version is 11+?
- [ ] JAVA_HOME environment variable set?
- [ ] Android SDK installed with API 34?
- [ ] Gradle wrapper in repository?
- [ ] Network connection for dependencies?
- [ ] No unsaved changes in IDE?
- [ ] Gradle cache clean?
- [ ] Physical device/emulator connected?

---

## Contact & Support

For questions or issues:
1. Check existing GitHub issues
2. Review ARCHITECTURE.md
3. Check Android Developer documentation
4. Ask on Stack Overflow with appropriate tags

