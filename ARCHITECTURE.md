# Architecture Documentation

## System Architecture Overview

The Android Attendance System follows a layered architecture pattern with clear separation of concerns:

```
┌──────────────────────────────────────────────────┐
│              Presentation Layer                  │
│  (Activities, Fragments, Resources)              │
│  - 14 Activities handling UI and user input      │
│  - XML layout files for UI design                │
│  - Resource files (strings, colors, styles)      │
└─────────────────┬────────────────────────────────┘
                  │
┌─────────────────▼────────────────────────────────┐
│            Business Logic Layer                  │
│  (Application Context, Managers, Services)       │
│  - Application-wide state management             │
│  - Business rule implementation                  │
│  - Data validation and processing                │
└─────────────────┬────────────────────────────────┘
                  │
┌─────────────────▼────────────────────────────────┐
│             Data Access Layer                    │
│  (Database, DAOs, Repositories)                  │
│  - SQLite database operations                    │
│  - Data entities (Beans)                         │
│  - CRUD operations                               │
└──────────────────────────────────────────────────┘
```

---

## Component Breakdown

### **1. Presentation Layer (Activities & Resources)**

#### Activities Structure:
```
com.android.attendance.activity/
├── MainActivity
│   └── Entry point for the application
│
├── LoginActivity
│   └── User authentication
│
├── MenuActivity
│   └── Main navigation and role selection
│
├── AddFacultyActivity
│   └── Create/Edit faculty records
│
├── AddStudentActivity
│   └── Create/Edit student records
│
├── ViewFacultyActivity
│   └── Display faculty list
│
├── ViewStudentActivity
│   └── Display all students
│
├── ViewStudentByBranchYear
│   └── Filter students by branch/year
│
├── AddAttandanceSessionActivity
│   └── Setup attendance session
│
├── AddAttendanceActivity
│   └── Mark attendance for selected students
│
├── ViewAttendanceByFacultyActivity
│   └── Faculty-wise attendance reports
│
├── ViewAttendancePerStudentActivity
│   └── Student-specific attendance history
│
└── TestActivity
    └── Debugging and testing utilities
```

#### Resource Files:
```
res/
├── layout/
│   ├── activity_main.xml - Main dashboard
│   ├── login.xml - Login form
│   ├── menu.xml - Navigation menu
│   ├── addfaculty.xml - Faculty form
│   ├── addstudent.xml - Student form
│   ├── add_attandance.xml - Attendance marking
│   └── [14 layout files total]
│
├── values/
│   ├── strings.xml - Text resources (multilingual)
│   ├── colors.xml - Color definitions
│   ├── styles.xml - Theme and style definitions
│   ├── dimens.xml - Dimension values
│   └── attrs.xml - Custom attributes
│
├── drawable/
│   ├── ic_launcher.png - App icon
│   ├── roundedbutton.xml - Button styles
│   └── [notification resources]
│
└── menu/
    └── main.xml - Menu configuration
```

---

### **2. Business Logic Layer**

#### Application Context:
```
com.android.attendance.context/
└── ApplicationContext
    ├── Singleton instance management
    ├── Global state initialization
    ├── Resource access point
    └── Application lifecycle management
```

#### Data Models (Beans):
```
com.android.attendance.bean/
├── Student
│   ├── rollNo: String
│   ├── name: String
│   ├── branch: String
│   ├── year: int
│   └── email: String
│
├── Faculty
│   ├── id: int
│   ├── name: String
│   ├── department: String
│   └── email: String
│
├── Teacher
│   ├── id: int
│   ├── name: String
│   ├── qualifications: String
│   └── phone: String
│
├── Attendance
│   ├── id: int
│   ├── studentId: String
│   ├── date: String
│   ├── status: String (Present/Absent)
│   └── subject: String
│
└── AttendanceSession
    ├── id: int
    ├── class: String
    ├── subject: String
    ├── date: String
    └── facultyId: int
```

---

### **3. Data Access Layer**

#### Database Architecture:
```
com.android.attendance.db/
├── DatabaseHelper (extends SQLiteOpenHelper)
│   ├── onCreate() - Initial database schema creation
│   ├── onUpgrade() - Database version management
│   └── Database tables:
│       ├── STUDENTS
│       ├── FACULTY
│       ├── TEACHERS
│       ├── ATTENDANCE
│       ├── SESSIONS
│       ├── SUBJECTS
│       └── CLASSES
│
└── DAOs (Data Access Objects)
    ├── StudentDAO
    │   ├── createStudent()
    │   ├── getAllStudents()
    │   ├── getStudentByRoll()
    │   ├── updateStudent()
    │   └── deleteStudent()
    │
    ├── AttendanceDAO
    │   ├── markAttendance()
    │   ├── getAttendanceByStudent()
    │   ├── getAttendanceByDate()
    │   └── generateReport()
    │
    ├── FacultyDAO
    ├── TeacherDAO
    └── SessionDAO
```

#### Database Schema:
```sql
-- STUDENTS Table
CREATE TABLE STUDENTS (
    roll_no TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    branch TEXT,
    year INTEGER,
    email TEXT,
    created_at TIMESTAMP
);

-- FACULTY Table
CREATE TABLE FACULTY (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    department TEXT,
    email TEXT,
    created_at TIMESTAMP
);

-- ATTENDANCE Table
CREATE TABLE ATTENDANCE (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    student_id TEXT,
    date TEXT NOT NULL,
    status TEXT (Present/Absent),
    subject TEXT,
    session_id INTEGER,
    FOREIGN KEY(student_id) REFERENCES STUDENTS(roll_no),
    FOREIGN KEY(session_id) REFERENCES SESSIONS(id)
);

-- SESSIONS Table
CREATE TABLE SESSIONS (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    class TEXT,
    subject TEXT,
    date TEXT,
    faculty_id INTEGER,
    FOREIGN KEY(faculty_id) REFERENCES FACULTY(id)
);
```

---

## Data Flow

### Authentication Flow:
```
1. User launches MainActivity
   ↓
2. Redirected to LoginActivity
   ↓
3. Credentials validated against local database
   ↓
4. Role determined (Admin/Teacher)
   ↓
5. Navigate to MenuActivity
   ↓
6. Role-based menu displayed
```

### Attendance Marking Flow:
```
1. Teacher selects "Mark Attendance"
   ↓
2. AddAttandanceSessionActivity loads
   ↓
3. Select class, subject, date
   ↓
4. AddAttendanceActivity displays student list
   ↓
5. Toggle presence/absence for each student
   ↓
6. Data persisted to SQLite database
   ↓
7. Confirmation shown to user
```

### Data Retrieval & Reporting:
```
1. User requests attendance report
   ↓
2. ViewAttendanceActivity queries database
   ↓
3. Fetch relevant records based on filters
   ↓
4. Process and format data
   ↓
5. Display in ListView/RecyclerView
   ↓
6. Option to export or share
```

---

## Offline-First Architecture

- All data stored locally on device SQLite database
- No network connection required for core functionality
- Sync feature (optional) for multi-device scenarios
- User data remains accessible even offline

---

## Security Considerations

### Current Implementation:
- Local authentication with stored credentials
- SQLite database on device (encrypted by default on newer Android versions)
- Role-based access control (Admin vs Teacher)

### Recommendations for Production:
- Implement encrypted SharedPreferences for sensitive data
- Use keystore for credential storage
- Add network encryption (TLS) if implementing backend
- Implement proper session management
- Add audit logging for admin actions

---

## Performance Optimization

### Current Optimizations:
- Local database queries (no network latency)
- Efficient SQLite indexing
- Activity-based UI updates (no heavy fragments)
- ProGuard code obfuscation for smaller APK size

### Future Improvements:
- Implement pagination for large datasets
- Add database query caching
- Lazy load images and resources
- Implement view recycling in ListViews

---

## Scalability

### Current Limitations:
- Single-device usage
- Local SQLite database (suitable for ~10K records)
- No real-time synchronization

### Scaling Strategies:
- Implement backend API (REST/GraphQL)
- Add Firebase for cloud syncing
- Implement proper database migrations
- Add queue system for offline operations

---

## Testing Strategy

### Unit Testing:
- Test individual beans and data models
- Test database CRUD operations
- Test business logic calculations

### Integration Testing:
- Test activity workflows
- Test data persistence across activities
- Test attendance calculation logic

### UI Testing:
- Test login flow
- Test attendance marking workflow
- Test report generation

---

## Dependencies & Libraries

### Core Android:
- `androidx.appcompat:appcompat:1.6.1`
- `androidx.constraintlayout:constraintlayout:2.1.4`
- `com.google.android.material:material:1.9.0`

### Database:
- `androidx.sqlite:sqlite:2.4.0`

### Testing:
- `junit:junit:4.13.2`
- `androidx.test.ext:junit:1.1.5`
- `androidx.test.espresso:espresso-core:3.5.1`

---

## Build Configuration

### Gradle Configuration:
- Minimum SDK: 21 (Android 5.0)
- Target SDK: 34 (Android 14)
- Java: Java 11+
- Gradle: 8.2
- Android Gradle Plugin: 8.1.2

### Build Variants:
- **Debug**: Full features, debuggable
- **Release**: Optimized, obfuscated with ProGuard

---

## Deployment

### APK Generation:
```bash
# Debug APK
./gradlew assembleDebug

# Release APK (requires signing)
./gradlew assembleRelease
```

### Device Installation:
```bash
# Via ADB
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Via gradlew
./gradlew installDebug
```

---

## Future Architecture Improvements

1. **MVVM Pattern**: Migrate from Activity-based to MVVM
2. **Coroutines**: Replace callbacks with Kotlin coroutines
3. **Room Database**: Migrate from raw SQLite to Room
4. **Dependency Injection**: Add Dagger/Hilt for DI
5. **Navigation Component**: Use AndroidX Navigation instead of Intent-based
6. **ViewModel & LiveData**: Implement lifecycle-aware components
7. **Repository Pattern**: Abstract data sources with repository layer
