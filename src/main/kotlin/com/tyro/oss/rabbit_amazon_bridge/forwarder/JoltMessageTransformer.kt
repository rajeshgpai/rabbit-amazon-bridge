/*
 * Copyright [2018] Tyro Payments Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tyro.oss.rabbit_amazon_bridge.forwarder

import com.bazaarvoice.jolt.Chainr
import com.bazaarvoice.jolt.JsonUtils

open class JoltMessageTransformer(val chainr: Chainr) : MessageTransformer {
    override fun transform(message: String): String {
        val transformedObject = chainr.transform(JsonUtils.jsonToObject(message)) ?: emptyMap<String, String>()
        return JsonUtils.toJsonString(transformedObject)
    }
}