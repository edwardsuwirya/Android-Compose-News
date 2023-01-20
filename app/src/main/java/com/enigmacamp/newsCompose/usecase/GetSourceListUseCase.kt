package com.enigmacamp.newsCompose.usecase

import com.enigmacamp.newsCompose.model.Source

fun interface GetSourceListUseCase : suspend () -> List<Source>