package com.example.locaui.model

class WebMarks {

    private var contentId: Int = 0
    var webName: String? = null
    var webUrl: String? = null
    var webDesc: String? = null

    fun getContentId(): Int {
        return contentId
    }

    fun setContentId(contentId: Int) {
        this.contentId = contentId
    }

    constructor(contentId: Int, webName: String, webUrl: String, webDesc: String) {
        this.contentId = contentId
        this.webName = webName
        this.webUrl = webUrl
        this.webDesc = webDesc
    }

    constructor() {

    }


}
