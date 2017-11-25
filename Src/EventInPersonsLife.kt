package FluentSample.Src

enum class EventInPersonsLife {
    Birth
    {
        override fun toState() = "Single"
        override fun toHistory() = "was born"
    },
    Marriage
    {
        override fun toState() = "Married"
        override fun toHistory() = "has married"
    },
    Divorce
    {
        override fun toState() = "Divorced"
        override fun toHistory() = "was divorced"
    },
    Widowhood
    {
        override fun toState() = "Widowed"
        override fun toHistory() = "was widowed"
    },
    Death
    {
        override fun toState() = "Dead"
        override fun toHistory() = "died"
    },
    ReBirth
    {
        override fun toState() = "Single"
        override fun toHistory() = "was reborn"
    };

    open fun toState() = this.toString()
    open fun toHistory() = this.toString()
}


