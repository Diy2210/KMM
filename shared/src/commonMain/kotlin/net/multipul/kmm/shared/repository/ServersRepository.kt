//package net.multipul.kmm.shared.repository
//
//import com.squareup.sqldelight.db.SqlDriver
//import net.multipul.kmm.Server
//import net.multipul.kmm.Servers
//
//class ServersRepository(slqDriver: SqlDriver) {
//
//    private val database = Servers(slqDriver)
//    private val serverQueries = database.serverQueries
//
//    fun getAll(): List<Server> {
//        return serverQueries.selectAll().executeAsList()
//    }
//
//    fun get(id: Long): Server {
//        return serverQueries.selectByID(id).executeAsOne()
//    }
//
//    fun save(id: Long, model: Server) {
//        if (id > 0) {
//            serverQueries.update(
//                    model.title,
//                    model.url,
//                    model.token,
//                    id
//            )
//        } else {
//            serverQueries.insert(
//                    model.title,
//                    model.url,
//                    model.token
//            )
//        }
//    }
//
//    fun delete(id: Long) {
//        serverQueries.deleteByID(id)
//    }
//}