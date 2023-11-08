package bitblt

interface WinGDIConf {
    companion object {
        // SRCCOPY : 복사한다.
        const val SRCCOPY = 0x00CC0020
        const val SRCPAINT = 0x00ee0086
        const val SRCAND = 0x008800c6
        const val SRCINVERT = 0x00660046
        const val SRCERASE = 0x00440328
        const val NOTSRCCOPY = 0x00330008
        const val NOTSRCERASE = 0x001100a6

        // MERGECOPY : 비트맵을 AND 연산한다.
        const val MERGECOPY = 0x00c000ca

        // MERGEPAINT : 비트맵을 OR 연산한다.
        const val MERGEPAINT = 0x00bb0226
        const val PATCOPY = 0x00f00021
        const val PATPAINT = 0x00fb0a09
        const val PATINVERT = 0x005a0049

        // DSTINVERT : 화면을 반전시킨다.
        const val DSTINVERT = 0x00550009

        // WHITENESS : 대상영역을 흰색으로 채운다.
        const val WHITENESS = 0x00ff0062

        // BLACKNESS : 대상영역을 검정색으로 채운다.
        const val BLACKNESS = 0x00000042
        const val CAPTUREBLT = 0x00CC0020 or 0x40000000
        const val Black = 0x00000000
        const val WS_CHILD = 0x40000000L
        const val WS_VISIBLE = 0x10000000L
        const val MS_SHOWMAGNIFIEDCURSOR = 0x0001L
        const val WS_EX_TOPMOST = 0x00000008L
        const val WS_EX_LAYERED: Long = 0x00080000
        const val WS_EX_TRANSPARENT = 0x00000020L
        const val WS_CLIPCHILDREN = 0x02000000L
        const val MW_FILTERMODE_EXCLUDE: Long = 0
    }
}
