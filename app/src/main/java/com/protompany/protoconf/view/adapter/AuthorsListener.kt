package com.protompany.protoconf.view.adapter

import com.protompany.protoconf.model.Author


interface AuthorsListener {
    fun onAuthorsClicked(author: Author, position: Int)
}