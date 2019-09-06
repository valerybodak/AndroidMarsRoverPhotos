package com.module.domain.entities

//Please see list of cameras:
//https://api.nasa.gov/api.html#MarsPhotos
enum class RoverCamera (val id: String) {
    FHAZ("fhaz"),
    RHAZ("rhaz"),
    MAST("mast"),//portuguese
    CHEMCAM("chemcam"),
    MAHLI("mahli"),
    MARDI("mardi"),
    NAVCAM("navcam"),
    PANCAM("pancam"),
    MINITES("minites")
}