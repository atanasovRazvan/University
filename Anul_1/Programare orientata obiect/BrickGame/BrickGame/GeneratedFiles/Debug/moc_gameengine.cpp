/****************************************************************************
** Meta object code from reading C++ file 'gameengine.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.12.3)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../gameengine.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'gameengine.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.12.3. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_BrickGameEngine_t {
    QByteArrayData data[14];
    char stringdata0[130];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_BrickGameEngine_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_BrickGameEngine_t qt_meta_stringdata_BrickGameEngine = {
    {
QT_MOC_LITERAL(0, 0, 15), // "BrickGameEngine"
QT_MOC_LITERAL(1, 16, 12), // "scoreChanged"
QT_MOC_LITERAL(2, 29, 0), // ""
QT_MOC_LITERAL(3, 30, 12), // "currentScore"
QT_MOC_LITERAL(4, 43, 11), // "deadChanged"
QT_MOC_LITERAL(5, 55, 13), // "currentNrDead"
QT_MOC_LITERAL(6, 69, 12), // "advanceBoard"
QT_MOC_LITERAL(7, 82, 12), // "brickCreated"
QT_MOC_LITERAL(8, 95, 1), // "x"
QT_MOC_LITERAL(9, 97, 1), // "y"
QT_MOC_LITERAL(10, 99, 6), // "brickW"
QT_MOC_LITERAL(11, 106, 6), // "brickH"
QT_MOC_LITERAL(12, 113, 12), // "gameFinished"
QT_MOC_LITERAL(13, 126, 3) // "win"

    },
    "BrickGameEngine\0scoreChanged\0\0"
    "currentScore\0deadChanged\0currentNrDead\0"
    "advanceBoard\0brickCreated\0x\0y\0brickW\0"
    "brickH\0gameFinished\0win"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_BrickGameEngine[] = {

 // content:
       8,       // revision
       0,       // classname
       0,    0, // classinfo
       5,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       5,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    1,   39,    2, 0x06 /* Public */,
       4,    1,   42,    2, 0x06 /* Public */,
       6,    0,   45,    2, 0x06 /* Public */,
       7,    4,   46,    2, 0x06 /* Public */,
      12,    1,   55,    2, 0x06 /* Public */,

 // signals: parameters
    QMetaType::Void, QMetaType::Int,    3,
    QMetaType::Void, QMetaType::Int,    5,
    QMetaType::Void,
    QMetaType::Void, QMetaType::Int, QMetaType::Int, QMetaType::Int, QMetaType::Int,    8,    9,   10,   11,
    QMetaType::Void, QMetaType::Bool,   13,

       0        // eod
};

void BrickGameEngine::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<BrickGameEngine *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->scoreChanged((*reinterpret_cast< int(*)>(_a[1]))); break;
        case 1: _t->deadChanged((*reinterpret_cast< int(*)>(_a[1]))); break;
        case 2: _t->advanceBoard(); break;
        case 3: _t->brickCreated((*reinterpret_cast< int(*)>(_a[1])),(*reinterpret_cast< int(*)>(_a[2])),(*reinterpret_cast< int(*)>(_a[3])),(*reinterpret_cast< int(*)>(_a[4]))); break;
        case 4: _t->gameFinished((*reinterpret_cast< bool(*)>(_a[1]))); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        {
            using _t = void (BrickGameEngine::*)(int );
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&BrickGameEngine::scoreChanged)) {
                *result = 0;
                return;
            }
        }
        {
            using _t = void (BrickGameEngine::*)(int );
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&BrickGameEngine::deadChanged)) {
                *result = 1;
                return;
            }
        }
        {
            using _t = void (BrickGameEngine::*)();
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&BrickGameEngine::advanceBoard)) {
                *result = 2;
                return;
            }
        }
        {
            using _t = void (BrickGameEngine::*)(int , int , int , int );
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&BrickGameEngine::brickCreated)) {
                *result = 3;
                return;
            }
        }
        {
            using _t = void (BrickGameEngine::*)(bool );
            if (*reinterpret_cast<_t *>(_a[1]) == static_cast<_t>(&BrickGameEngine::gameFinished)) {
                *result = 4;
                return;
            }
        }
    }
}

QT_INIT_METAOBJECT const QMetaObject BrickGameEngine::staticMetaObject = { {
    &QObject::staticMetaObject,
    qt_meta_stringdata_BrickGameEngine.data,
    qt_meta_data_BrickGameEngine,
    qt_static_metacall,
    nullptr,
    nullptr
} };


const QMetaObject *BrickGameEngine::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *BrickGameEngine::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_BrickGameEngine.stringdata0))
        return static_cast<void*>(this);
    return QObject::qt_metacast(_clname);
}

int BrickGameEngine::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QObject::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 5)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 5;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 5)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 5;
    }
    return _id;
}

// SIGNAL 0
void BrickGameEngine::scoreChanged(int _t1)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 0, _a);
}

// SIGNAL 1
void BrickGameEngine::deadChanged(int _t1)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 1, _a);
}

// SIGNAL 2
void BrickGameEngine::advanceBoard()
{
    QMetaObject::activate(this, &staticMetaObject, 2, nullptr);
}

// SIGNAL 3
void BrickGameEngine::brickCreated(int _t1, int _t2, int _t3, int _t4)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)), const_cast<void*>(reinterpret_cast<const void*>(&_t2)), const_cast<void*>(reinterpret_cast<const void*>(&_t3)), const_cast<void*>(reinterpret_cast<const void*>(&_t4)) };
    QMetaObject::activate(this, &staticMetaObject, 3, _a);
}

// SIGNAL 4
void BrickGameEngine::gameFinished(bool _t1)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 4, _a);
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
