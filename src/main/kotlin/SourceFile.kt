package vision.kodai.xemime

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import java.io.File

sealed class SrcState {
    object Unchecked: SrcState()
    object Exist: SrcState()
    object NotFound: SrcState()
}

class SourceFile<S: SrcState> private constructor(val file: File) {
    companion object {
        fun create(uncheckedFile: File): SourceFile<SrcState.Unchecked> =
            SourceFile(uncheckedFile)
    }

    override fun toString(): String = file.absolutePath
}

typealias CheckResult = Either<SourceFile<SrcState.NotFound>, SourceFile<SrcState.Exist>>

@Suppress("UNCHECKED_CAST")
fun SourceFile<SrcState.Unchecked>.check(): CheckResult =
    if (this.file.exists()) {
        Right(this as SourceFile<SrcState.Exist>)
    } else {
        Left(this as SourceFile<SrcState.NotFound>)
    }
