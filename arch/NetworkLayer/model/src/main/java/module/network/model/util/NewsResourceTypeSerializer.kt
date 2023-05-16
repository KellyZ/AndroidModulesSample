package module.network.model.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import module.network.model.NewsResourceType
import module.network.model.asNewsResourceType

object NewsResourceTypeSerializer: KSerializer<NewsResourceType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "type",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): NewsResourceType {
        return decoder.decodeString().asNewsResourceType()
    }

    override fun serialize(encoder: Encoder, value: NewsResourceType) {
        return encoder.encodeString(value.serializedName)
    }
}