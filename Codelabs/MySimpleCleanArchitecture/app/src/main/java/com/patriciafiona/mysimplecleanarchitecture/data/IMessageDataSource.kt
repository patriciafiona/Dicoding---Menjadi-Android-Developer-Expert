package com.patriciafiona.mysimplecleanarchitecture.data

import com.patriciafiona.mysimplecleanarchitecture.domain.MessageEntity

interface IMessageDataSource {
    fun getMessageFromSource(name: String): MessageEntity
}