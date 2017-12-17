package FluentSampleTests

import FluentSample.Src.EventInPersonsLife
import FluentSample.Src.Person
import FluentSample.Src.ReportStyle
import FluentSample.Src.reportStateVia
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestFluentSample {
    fun reportHelperFunction(msg: String): String {
        return msg
    }

    @Test
    fun noConstructor() {
        val person = Person.getsBorn(name = "x")
    }

    @Test
    fun assertOnStatePossible() {
        val person = Person.getsBorn(name = "x")

        val currentState = person.getCurrentState()

        assertEquals(currentState, EventInPersonsLife.Birth)
    }

    @Test
    fun assertOnStateInFluentPossible() {
        val person = Person.getsBorn(name = "x")
        var stateAsString: String = ""

        person.getsMarried().reportStateVia({ s -> stateAsString = reportHelperFunction(s) })
        val currentState = person.getCurrentState()
        assertEquals(currentState, EventInPersonsLife.Birth)

        assertTrue { stateAsString.contains(EventInPersonsLife.Marriage.toState()) }
    }

    @Test
    fun firstOne() {
        Person.getsBorn("Henry").reportStateVia(::println).
                getsMarried().reportStateVia(::println).reportStateVia(::println, ReportStyle.ReportAll)
    }

    @Test
    fun complexOne() {
        Person.getsBorn("Klaus").getsMarried().getsDivorced().dies().getsReborn()
        Person.getsBorn("Henry").reportStateVia(::println).
                getsMarried().reportStateVia(::println).
                getsDivorced().reportStateVia(::println).
                dies().reportStateVia(::println).
                getsReborn().reportStateVia(::println)
    }

    @Test
    fun testNewWay() {
        Person.getsBorn("NewWay").reportState(::println)
                .dies().reportState(::println)
                .getsReborn().reportState(::println)
                .getsMarried().reportState(::println)
                .getsWidowed().reportState(::println)
                .dies().reportState(::println, ReportStyle.ReportAll)
    }

    @Test
    fun testOtherReport() {
        Person.getsBorn("Wer?").reportOther(::println).reportOther { println(it) }
    }
}
