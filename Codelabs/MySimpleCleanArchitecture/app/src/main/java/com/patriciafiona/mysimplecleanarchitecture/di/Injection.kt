package com.patriciafiona.mysimplecleanarchitecture.di

import com.patriciafiona.mysimplecleanarchitecture.data.IMessageDataSource
import com.patriciafiona.mysimplecleanarchitecture.data.MessageDataSource
import com.patriciafiona.mysimplecleanarchitecture.data.MessageRepository
import com.patriciafiona.mysimplecleanarchitecture.domain.IMessageRepository
import com.patriciafiona.mysimplecleanarchitecture.domain.MessageInteractor
import com.patriciafiona.mysimplecleanarchitecture.domain.MessageUseCase

object Injection {
    fun provideUseCase(): MessageUseCase {
        val messageRepository = provideRepository()
        return MessageInteractor(messageRepository)
    }

    private fun provideRepository(): IMessageRepository {
        val messageDataSource = provideDataSource()
        return MessageRepository(messageDataSource)
    }

    private fun provideDataSource(): IMessageDataSource {
        return MessageDataSource()
    }
}