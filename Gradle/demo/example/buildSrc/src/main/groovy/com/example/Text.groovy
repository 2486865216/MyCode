package com.example;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * author ye
 * createDate 2022/8/7  19:53
 */
class Text implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task("testMyPlugin") {
            doLast {
                println("自定义的插件")
            }
        }
    }
}
