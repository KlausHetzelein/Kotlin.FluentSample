package FluentSample.Src

class Person private constructor(var name: String) : IAnyPerson, IAlivePerson, IDeadPerson, IUnmarriedPerson, IMarriedPerson {
    override fun reportOther(function: (String) -> Unit): IAnyPerson {
        function("Na ein erster Test...")
        return this
    }

    override fun reportState(function: (String) -> Unit, reportStyle: ReportStyle): Person {
        reportStateTo(function, reportStyle)
        return this
    }

    override fun getCurrentState(): EventInPersonsLife {
        return events[0]
    }

    private var events = mutableListOf<EventInPersonsLife>()

    // ersatz für static factorymethod and to make Person-ctor private
    companion object {
        fun getsBorn(name: String): IUnmarriedPerson {
            return Person(name)
        }
    }

    init {
        events.add(EventInPersonsLife.Birth)
    }

    private fun reportStateTo(function: (String) -> Unit, reportStyle: ReportStyle) {
        var msg: String
        if (reportStyle == ReportStyle.ReportOnlyCurrentState) {
            msg = "${name}'s current state is: ${events[events.size - 1].toState()}"
        } else {
            msg = "Following ${name}'s states\n"
            var counter = 0
            for (event in events) {
                msg += "${++counter} ${event.toHistory()} \n"
            }
        }

        function(msg)
    }

    override fun getsMarried(): IMarriedPerson {
        events.add(EventInPersonsLife.Marriage)
        return this
    }

    override fun getsWidowed(): IUnmarriedPerson {
        events.add(EventInPersonsLife.Widowhood)
        return this
    }

    override fun getsDivorced(): IUnmarriedPerson {
        events.add(EventInPersonsLife.Divorce)
        return this
    }

    override fun dies(): IDeadPerson {
        events.add(EventInPersonsLife.Death)
        return this
    }

    override fun getsReborn(): IUnmarriedPerson {
        events.add(EventInPersonsLife.ReBirth)
        return this
    }
}