package com.hogent.android.network.dtos.response

import com.hogent.android.data.entities.Project

class ProjectResponse(val projects : List<Project>) {
}
class ProjectResponseAfterCreate(val projectId : Int){}