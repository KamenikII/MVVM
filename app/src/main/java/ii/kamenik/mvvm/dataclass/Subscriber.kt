package ii.kamenik.mvvm.dataclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**Этот датакласс объеденён с entity, для простоты скелета. Но в проекте это должны быть разные уровни.*/

@Entity(tableName = "sub_data_table")
data class Subscriber(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sub_id")
    var id: Int,

    @ColumnInfo(name = "sub_name")
    var name: String,

    @ColumnInfo(name = "sub_email")
    var email: String,

)
