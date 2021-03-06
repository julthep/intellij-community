// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.find.usages.api

import com.intellij.model.search.Searcher
import com.intellij.usages.Usage

/**
 * Convenience interface for searchers providing additional queries to find [Usage]s by [UsageSearchParameters].
 */
interface UsageSearcher : Searcher<UsageSearchParameters, Usage>
