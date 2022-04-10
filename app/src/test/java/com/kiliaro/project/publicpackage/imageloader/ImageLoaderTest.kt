package com.kiliaro.project.publicpackage.imageloader


import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ImageLoaderTest {

    @Test
    fun `addNeededParametersToUrl should add needed parameters to url`() {
        val width = 25
        val height = 80
        val resizeMode = ServerResizeMode.Crop
        val url =
            "https://imgdc1.kiliaro.com/shared/djlCbGusTJamg_ca4axEVw/imageresize/i/60cc705d0025904750ee22d300020eb4/0.jpg"
        val expectedUrl = "$url?w=25&h=80&m=crop"
        assertThat(
            expectedUrl,
            `is`(ImageLoader.addNeededParametersToUrl(url, width, height, resizeMode).toString())
        )
    }
}