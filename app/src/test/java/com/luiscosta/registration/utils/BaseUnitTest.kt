package com.luiscosta.registration.utils

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.JFixture
import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class BaseUnitTest<Sut : Any> {

    lateinit var fixture: JFixture
    lateinit var sut: Sut

    abstract fun buildSut(): Sut

    @Before
    fun setup() {
        fixture = JFixture()
        FixtureAnnotations.initFixtures(this, fixture)
        MockitoAnnotations.initMocks(this)

        sut = buildSut()
    }
}