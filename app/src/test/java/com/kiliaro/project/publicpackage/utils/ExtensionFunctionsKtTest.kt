package com.kiliaro.project.publicpackage.utils

import junit.framework.TestCase.assertNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

import org.junit.Test

class ExtensionFunctionsKtTest {

    @Test
    fun `toMoreReadableDateFormat() should return correct result for correct date format`() {
        val stringWithCorrectFormat = "2021-06-18T10:07:25Z"
        val expectedResult = "18/06/2021"
        assertThat(stringWithCorrectFormat.toMoreReadableDateFormat(), `is`(expectedResult))
    }

    @Test
    fun `toMoreReadableDateFormat() should return null for wrong date format`() {
        val stringWithCorrectFormat = "not formatted date"
        assertNull(stringWithCorrectFormat.toMoreReadableDateFormat())
    }

    @Test
    fun `byteIntToMbString() should return in megaByte formatted string fo positive values`() {
        val byteInput = 8562574
        assertThat(byteInput.byteIntToMbString(), `is`("8.2MB"))
    }

    @Test
    fun `byteIntToMbString() should return without point if the result fractional part is zero`() {
        val byteInput = 1048576
        assertThat(byteInput.byteIntToMbString(), `is`("1MB"))
    }

    @Test
    fun `byteIntToMbString() should return  0 if number is negative`() {
        val byteInput = -512
        assertThat(byteInput.byteIntToMbString(), `is`("0MB"))
    }
}