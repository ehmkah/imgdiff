# Changelog

## [Unreleased]
### Added

### Changed
- update dependencies
- Updated gradle
### Deprecated

### Removed

### Fixed

## [1.11.0]

### Fixed
- [Bugfix - plugin works under Intellij 2021.2](https://github.com/ehmkah/imgdiff/issues/69) 
    
## [1.10.0]

### Fixed
- [bugfix - plugin works under Intellij 2021.1](https://github.com/ehmkah/imgdiff/issues/66)
- 
### Changed
- upgrading dependencies
- moving CI from travis to github-actions
    
## [1.9.0]

### Added
- [Spot differences easier with blinking diff. Experimental feature, feedback welcome if
  it works strange for your work. Thanks to https://github.com/grigala" George Grigalashvili for helping to build it.](https://github.com/ehmkah/imgdiff/issues/60)
- internal: update gradle and dependencies

## [1.8.0]

### Fixed
- [bugfix if baseparent is null](https://github.com/ehmkah/imgdiff/issues/56)

### Changed     
- Plugin build with java11 and needs idea version at least 202 (e.g. IntelliJ 2020.3)
- update to intellij 2020.3 and update used gradle versions
       
## [1.7.0]

### Fixed 
- [bugfix: no crash if image can not be read](https://github.com/ehmkah/imgdiff/issues/52)

### Changed
- update dependencies
    
## [1.6.0]

### Changed
- update dependencies
    
## [1.5.0]

### Added
- [feature: Show original image in diff as background](https://github.com/ehmkah/imgdiff/issues/26">issue/26)

### Changed
- update dependencies<

## [1.4.0]

### Fixed
- [bugfix: fixed compare images in directory](https://github.com/ehmkah/imgdiff/issues/43)

### Changed
- upgrade to intellij 2020.1
- upgrade to gradle 6.3
- updating used gradle-plugins
- [upgrade to junit 5.6](https://github.com/ehmkah/imgdiff/issues/16)
  
## [1.3.0]

### Fixed
- [bugfix: jump to source works with defined-shortcut](https://github.com/ehmkah/imgdiff/issues/35">issue/35) 
  
## [1.2.0]

### Fixed
- [bugfix: show diff image for intellij 2019.3](https://github.com/ehmkah/imgdiff/issues/4)

### Removed
- [feature: removed old behavior](https://github.com/ehmkah/imgdiff/issues/33)

### Changed
- upgrade to gradle 6.0.1

## [1.1.0]

### Fixed
- [bugfix: jump to source from imgDiff fixed](https://github.com/ehmkah/imgdiff/issues/35)

### Changed
- upgrade to gradle 5.6.3
- moved last java class to kotlin
    
## [1.0.0]

### Added
- [feature: experimental show diff between two images. see README.adoc](https://github.com/ehmkah/imgdiff/issues/11)

### Changed
- upgrade to gradle 5.6.2

## [0.7.0]

### Fixed
- [bugfix: fixed memory leak](https://github.com/ehmkah/imgdiff/issues/24)

### Changed
- upgrade used kotlin and intellij gradle-plugin

## [0.6.0]

### Fixed
- [bugfix: better detect equal images](https://github.com/ehmkah/imgdiff/issues/20)

## [0.5.0]

### Added
- [feature: make it easier to identify identical images](https://github.com/ehmkah/imgdiff/issues/15)
- [feature: image for plugin](https://github.com/ehmkah/imgdiff/issues/6">issue/6)

### Changed
- [refactoring: update gradle to 5.6](https://github.com/ehmkah/imgdiff/issues/17)
- [refactoring: move source code to kotlin](https://github.com/ehmkah/imgdiff/issues/13)

## [0.4.0]

### Added
- [feature Short alt+F/ to open imgDiff](https://github.com/ehmkah/imgdiff/issues/9)
- [feature imgDiff-dialog is resizable](https://github.com/ehmkah/imgdiff/issues/8)

## [0.3.0]

### Fixed
- [bugfix dont crash if size differes](https://github.com/ehmkah/imgdiff/issues/1)

### Changed
- [changed color-schema for diff](https://github.com/ehmkah/imgdiff/issues/5)    

## [0.2.0] 

### Changed
- support gradle for build; support all jetbrain products; bugfix no crash if image size differs

## [0.1.0] 

### Added
- first offical release - very bugy
