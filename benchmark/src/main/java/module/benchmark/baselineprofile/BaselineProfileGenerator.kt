package module.benchmark.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import org.junit.Rule
import org.junit.Test

/**
 * Generates a baseline profile which can be copied to 'app/src/main/baseline-prof.txt'.
 */

class BaselineProfileGenerator {

    @get:Rule val baselineProfileRule = BaselineProfileRule()

    @Test
    fun generate() =
        baselineProfileRule.collectBaselineProfile("com.panda.template"){
            pressHome()
            startActivityAndWait()
            device.waitForIdle()

            val topicsTab = device.findObject(By.text("Topics"))
            topicsTab.click()
            device.waitForIdle()

//            val plantsTab = device.findObject(By.text("Plants"))
//            plantsTab.click()
//            device.waitForIdle()
//
//            device.wait(Until.hasObject(By.res("plants:list")), 3_000)
//            val plantList = device.findObject(By.res("plants:list"))
//            device.flingElementDownUp(plantList)
//
//            val plantItem = plantList.children[0]
//            plantItem.clickAndWait(Until.newWindow(), 1_000)
//            device.waitForIdle()
        }

}

fun UiDevice.flingElementDownUp(element: UiObject2) {
    element.setGestureMargin(displayWidth / 5)
    element.fling(Direction.DOWN)
    waitForIdle()
    element.fling(Direction.UP)
}