package com.cis.android_sqlite

class Task {
    var id: Int = 0
    var taskname: String = ""

    constructor(taskname: String) {
        this.taskname = taskname
    }

    constructor(id: Int, taskname: String) {
        this.id = id
        this.taskname = taskname
    }
}