package FluentSample.Src

// wird nie als neuer Zustand geliefert, dient als "Basis" für alle
interface IAnyPerson {
    fun reportState(function: (String) -> Unit,
                    reportStyle: ReportStyle = ReportStyle.ReportOnlyCurrentState)
            : IAnyPerson

    fun getCurrentState(): EventInPersonsLife
}

// Wird auch nie als Zustand geliefert,
// dient als Basis für alle lebenden Menschen, und bietet ":dies"
interface IAlivePerson : IAnyPerson {
    fun dies(): IDeadPerson

    override fun reportState(function: (String) -> Unit, reportStyle: ReportStyle)
            : IAlivePerson
}

interface IUnmarriedPerson : IAlivePerson {
    fun getsMarried(): IMarriedPerson

    override fun reportState(function: (String) -> Unit, reportStyle: ReportStyle)
            : IUnmarriedPerson
}

interface IMarriedPerson : IAlivePerson {
    fun getsDivorced(): IUnmarriedPerson
    fun getsWidowed(): IUnmarriedPerson

    override fun reportState(function: (String) -> Unit, reportStyle: ReportStyle)
            : IMarriedPerson
}

interface IDeadPerson : IAnyPerson {
    fun getsReborn(): IUnmarriedPerson

    override fun reportState(function: (String) -> Unit, reportStyle: ReportStyle)
            : IDeadPerson
}

// Das geht AUCH, ist aber nicht so schön, wenn man selbst die Interface designed
//

// Eine Methode in jedem Interface-Zustand der immer den Zustand beibehält
// und daher auch fluent hintereinander geschaltet werden kann.
// geht hier mit Extension-Methods (in java nicht möglich, in c# via explicit interface implementation)
fun IAlivePerson.reportStateVia(function: (String) -> Unit,
                                reportStyle: ReportStyle = ReportStyle.ReportOnlyCurrentState)
        : IAlivePerson {
    reportState(function, reportStyle)
    return this
}

fun IDeadPerson.reportStateVia(function: (String) -> Unit,
                               reportStyle: ReportStyle = ReportStyle.ReportOnlyCurrentState)
        : IDeadPerson {
    reportState(function, reportStyle)
    return this
}

fun IUnmarriedPerson.reportStateVia(function: (String) -> Unit,
                                    reportStyle: ReportStyle = ReportStyle.ReportOnlyCurrentState)
        : IUnmarriedPerson {
    reportState(function, reportStyle)
    return this
}

fun IMarriedPerson.reportStateVia(function: (String) -> Unit,
                                  reportStyle: ReportStyle = ReportStyle.ReportOnlyCurrentState)
        : IMarriedPerson {
    reportState(function, reportStyle)
    return this
}
