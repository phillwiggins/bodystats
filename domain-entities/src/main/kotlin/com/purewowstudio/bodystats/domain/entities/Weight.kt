package com.purewowstudio.bodystats.domain.entities

class Weight private constructor(
    private val value: Double,
    private val type: Type,
) : Comparable<Weight> {

    private val inGrams: Double
        get() = get(type = Type.GRAMS)

    val inKilograms: Double
        get() = get(type = Type.KILOGRAMS)

    val inPounds: Double
        get() = get(type = Type.POUNDS)

    private fun get(type: Type): Double =
        if (this.type == type) value else inGrams / type.gramsPerUnit

    internal fun zero(): Weight = ZEROS.getValue(type)

    override fun compareTo(other: Weight): Int =
        if (type == other.type) {
            value.compareTo(other.value)
        } else {
            inGrams.compareTo(other.inGrams)
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Weight) return false

        if (value != other.value) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

    override fun toString(): String = "$value ${type.name.lowercase()}"

    companion object {
        private val ZEROS = Type.values().associateWith { Weight(value = 0.0, type = it) }

        @JvmStatic
        fun grams(value: Double): Weight = Weight(value, Type.GRAMS)

        @JvmStatic
        fun kilograms(value: Double): Weight = Weight(value, Type.KILOGRAMS)

        @JvmStatic
        fun pounds(value: Double): Weight = Weight(value, Type.POUNDS)
    }

    private enum class Type {
        GRAMS {
            override val gramsPerUnit: Double = 1.0
        },
        KILOGRAMS {
            override val gramsPerUnit: Double = 1000.0
        },
        POUNDS {
            override val gramsPerUnit: Double = 453.59237
        };

        abstract val gramsPerUnit: Double
    }
}