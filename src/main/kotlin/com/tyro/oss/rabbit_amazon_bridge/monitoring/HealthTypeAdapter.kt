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
package com.tyro.oss.rabbit_amazon_bridge.monitoring

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.springframework.boot.actuate.health.Health
import java.lang.reflect.Type

class HealthTypeAdapter : JsonSerializer<Health> {

    override fun serialize(src: Health, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonObject().apply {

            src.status.code.let { addProperty("status", it) }
            src.status.description.takeIf { it.isNotEmpty() }?.let { addProperty("description", it) }
            src.details.forEach { key, value -> add(key, context.serialize(value)) }
        }
    }
}