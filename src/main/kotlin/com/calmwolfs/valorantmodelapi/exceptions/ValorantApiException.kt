package com.calmwolfs.valorantmodelapi.exceptions

class ValorantApiException(
    status: Int?,
    error: String
): Exception("$error ${if(status != null) "(${status})" else ""}")