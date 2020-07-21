package com.lamarrulla.reproductosservicios.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lamarrulla.reproductosservicios.dao.ActividadDao;
import com.lamarrulla.reproductosservicios.dao.FragmentMenuDao;
import com.lamarrulla.reproductosservicios.dao.TipoServicioDao;
import com.lamarrulla.reproductosservicios.dao.TipoNegocioDao;
import com.lamarrulla.reproductosservicios.dao.TipoVentaDao;
import com.lamarrulla.reproductosservicios.dao.UserDao;
import com.lamarrulla.reproductosservicios.dao.UserTipoNSV;
import com.lamarrulla.reproductosservicios.entity.Actividad;
import com.lamarrulla.reproductosservicios.entity.FragmentMenu;
import com.lamarrulla.reproductosservicios.entity.TipoNegocio;
import com.lamarrulla.reproductosservicios.entity.TipoServicio;
import com.lamarrulla.reproductosservicios.entity.TipoVenta;
import com.lamarrulla.reproductosservicios.entity.User;

@Database(entities = {User.class, Actividad.class, TipoServicio.class, TipoNegocio.class, TipoVenta.class, FragmentMenu.class, UserTipoNSV.class}, version = 4, exportSchema = false)
public abstract class REPSDatabase extends RoomDatabase {
    private static REPSDatabase instance;
    public abstract UserDao userDao();
    public abstract ActividadDao actividadDao();
    public abstract TipoServicioDao tipoServicioDao();
    public abstract TipoNegocioDao tipoNegocioDao();
    public abstract TipoVentaDao tipoVentaDao();
    public abstract FragmentMenuDao fragmentMenuDao();
    public abstract UserTipoNSV userTipoNSVDao();

    public static synchronized REPSDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), REPSDatabase.class, "reps_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserDao userDao;
        private ActividadDao actividadDao;
        private TipoServicioDao tipoServicioDao;
        private TipoNegocioDao tipoNegocioDao;
        private TipoVentaDao tipoVentaDao;
        private FragmentMenuDao fragmentMenuDao;
        private UserTipoNSV userTipoNSVDao;

        private PopulateDbAsyncTask(REPSDatabase db){
            actividadDao = db.actividadDao();
            userDao = db.userDao();
            tipoServicioDao = db.tipoServicioDao();
            tipoNegocioDao = db.tipoNegocioDao();
            tipoVentaDao = db.tipoVentaDao();
            fragmentMenuDao = db.fragmentMenuDao();
            userTipoNSVDao = db.userTipoNSVDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            fragmentMenuDao.insert(new FragmentMenu(1, 1, "Lista de Articulos", "ArticuloFragment", true));
            fragmentMenuDao.insert(new FragmentMenu(1, 2, "Agrega Articulos", "AgregaArticuloFragment", true));
            fragmentMenuDao.insert(new FragmentMenu(2, 1, "Datos Personales", "DatosPersonalesFragment", true));
            fragmentMenuDao.insert(new FragmentMenu(2, 2, "Datos Vendedor", "DatosVentaFragment", true));

            userDao.insert(new User("dave rincon", "dr@gmail.com", "55555555", "sierra dorada", "https://graph.facebook.com/3123179174379593/picture"));
            userDao.insert(new User("angel rincon", "ar@gmail.com", "55555555", "sierra dorada", "https://graph.facebook.com/3123179174379593/picture"));
            userDao.insert(new User("andres rincon", "andr@gmail.com", "55555555", "sierra dorada", "https://graph.facebook.com/3123179174379593/picture"));

            actividadDao.insert(new Actividad("Vendo", true));
            actividadDao.insert(new Actividad("Ofresco un servício", true));

            tipoVentaDao.insert(new TipoVenta("Negocio", true));
            tipoVentaDao.insert(new TipoVenta("Personal", true));

            tipoNegocioDao.insert(new TipoNegocio("Tienda", true));
            tipoNegocioDao.insert(new TipoNegocio("Taqueria", true));
            tipoNegocioDao.insert(new TipoNegocio("Cervecceria", true));
            tipoNegocioDao.insert(new TipoNegocio("Antojitos", true));
            tipoNegocioDao.insert(new TipoNegocio("Croqueteria", true));
            tipoNegocioDao.insert(new TipoNegocio("Ferreteria", true));
            tipoNegocioDao.insert(new TipoNegocio("Tlapaleria", true));
            tipoNegocioDao.insert(new TipoNegocio("Papeleria", true));
            tipoNegocioDao.insert(new TipoNegocio("Panaderia", true));
            tipoNegocioDao.insert(new TipoNegocio("Farmacia", true));
            tipoNegocioDao.insert(new TipoNegocio("Zapateria", true));
            tipoNegocioDao.insert(new TipoNegocio("Heladeria", true));


            tipoServicioDao.insert(new TipoServicio("Representante de ventas directas", true));
            tipoServicioDao.insert(new TipoServicio("Consultor", true));
            tipoServicioDao.insert(new TipoServicio("Acompañante de personas de la tercera edad", true));
            tipoServicioDao.insert(new TipoServicio("Tutor", true));
            tipoServicioDao.insert(new TipoServicio("Freelancer", true));
            tipoServicioDao.insert(new TipoServicio("Vloggero en YouTube", true));
            tipoServicioDao.insert(new TipoServicio("Reparación / detallado de automóviles", true));
            tipoServicioDao.insert(new TipoServicio("Rentas en economía colaborativa", true));
            tipoServicioDao.insert(new TipoServicio("Gestión de diseño web", true));
            tipoServicioDao.insert(new TipoServicio("Chambitas", true));
            tipoServicioDao.insert(new TipoServicio("Planificación de menús", true));
            tipoServicioDao.insert(new TipoServicio("Cuidador de mascotas", true));
            tipoServicioDao.insert(new TipoServicio("Cuidado del césped", true));
            tipoServicioDao.insert(new TipoServicio("Comida gourmet casera", true));
            tipoServicioDao.insert(new TipoServicio("Organizador profesional", true));
            tipoServicioDao.insert(new TipoServicio("Servicio de limpieza verde", true));
            tipoServicioDao.insert(new TipoServicio("Entrega de comestibles", true));
            tipoServicioDao.insert(new TipoServicio("Conserje personal", true));
            tipoServicioDao.insert(new TipoServicio("Crear productos informativos", true));
            tipoServicioDao.insert(new TipoServicio("Blogging", true));
            tipoServicioDao.insert(new TipoServicio("Limpieza de ventanas", true));
            tipoServicioDao.insert(new TipoServicio("Diseñador de interiores", true));
            tipoServicioDao.insert(new TipoServicio("Distribución de volantes", true));
            tipoServicioDao.insert(new TipoServicio("Programación", true));
            tipoServicioDao.insert(new TipoServicio("Asistente virtual", true));
            tipoServicioDao.insert(new TipoServicio("Consultor de medios sociales", true));
            tipoServicioDao.insert(new TipoServicio("Gestor de proyecto", true));
            tipoServicioDao.insert(new TipoServicio("Servicios de secretaría", true));
            tipoServicioDao.insert(new TipoServicio("Catering", true));
            tipoServicioDao.insert(new TipoServicio("Reparación móvil", true));
            tipoServicioDao.insert(new TipoServicio("Creación de CVs", true));
            tipoServicioDao.insert(new TipoServicio("Decorador de temporada", true));
            tipoServicioDao.insert(new TipoServicio("Compra de nombres de dominio", true));
            tipoServicioDao.insert(new TipoServicio("Reconstrucción de sitios web", true));
            tipoServicioDao.insert(new TipoServicio("Mantenimiento de lavadoras y secadoras", true));
            tipoServicioDao.insert(new TipoServicio("Guía de turismo", true));
            tipoServicioDao.insert(new TipoServicio("Marketing de afiliación", true));
            tipoServicioDao.insert(new TipoServicio("Dropshipping", true));
            tipoServicioDao.insert(new TipoServicio("Remoción de nieve", true));
            tipoServicioDao.insert(new TipoServicio("Aseo de mascotas", true));
            tipoServicioDao.insert(new TipoServicio("Recoge excremento", true));
            tipoServicioDao.insert(new TipoServicio("Planificador de eventos o fiestas", true));
            tipoServicioDao.insert(new TipoServicio("Reparación y mantenimiento de computadoras", true));
            tipoServicioDao.insert(new TipoServicio("Entrenador de vida o mentor", true));
            tipoServicioDao.insert(new TipoServicio("Contador", true));
            tipoServicioDao.insert(new TipoServicio("Preparación de impuestos ", true));
            tipoServicioDao.insert(new TipoServicio("Escribir libros", true));
            tipoServicioDao.insert(new TipoServicio("Instalación de pisos", true));
            tipoServicioDao.insert(new TipoServicio("Identificaciones de casa", true));
            tipoServicioDao.insert(new TipoServicio("Rayado de estacionamiento", true));
            tipoServicioDao.insert(new TipoServicio("Planificación de negocios", true));
            tipoServicioDao.insert(new TipoServicio("Jabones naturales y productos de belleza hechos en casa", true));
            tipoServicioDao.insert(new TipoServicio("Diseño de logotipos", true));
            tipoServicioDao.insert(new TipoServicio("Traductor", true));
            tipoServicioDao.insert(new TipoServicio("Restauración de muebles", true));
            tipoServicioDao.insert(new TipoServicio("Vendedor de eBay", true));
            tipoServicioDao.insert(new TipoServicio("Desarrollador de aplicaciones", true));
            tipoServicioDao.insert(new TipoServicio("Chef personal", true));
            tipoServicioDao.insert(new TipoServicio("Entrenador personal", true));
            tipoServicioDao.insert(new TipoServicio("Embellecer productos", true));
            tipoServicioDao.insert(new TipoServicio("Fotógrafo", true));
            tipoServicioDao.insert(new TipoServicio("Marcos para fotos", true));
            tipoServicioDao.insert(new TipoServicio("Ventas en mercados de pulgas / trueques", true));
            tipoServicioDao.insert(new TipoServicio("Productor de contenido en línea", true));
            tipoServicioDao.insert(new TipoServicio("Desarrollador de WordPress", true));
            tipoServicioDao.insert(new TipoServicio("Vender plantas en línea", true));
            tipoServicioDao.insert(new TipoServicio("Calígrafo", true));
            tipoServicioDao.insert(new TipoServicio("Vendedor de artesanías", true));
            tipoServicioDao.insert(new TipoServicio("Cuidado de niños", true));
            tipoServicioDao.insert(new TipoServicio("Limpieza de casas embargadas", true));
            tipoServicioDao.insert(new TipoServicio("Reciclaje de chatarra", true));
            tipoServicioDao.insert(new TipoServicio("Consultor de seguridad de Internet", true));
            tipoServicioDao.insert(new TipoServicio("Bartender independiente", true));
            tipoServicioDao.insert(new TipoServicio("Entrenamiento de perros", true));
            tipoServicioDao.insert(new TipoServicio("Servicio de referencia", true));
            tipoServicioDao.insert(new TipoServicio("Servicio de embalaje", true));
            tipoServicioDao.insert(new TipoServicio("Escribir y / o grabar una canción", true));
            tipoServicioDao.insert(new TipoServicio("Promoción de conciertos y espectáculos", true));
            tipoServicioDao.insert(new TipoServicio("Probador o revisor", true));
            tipoServicioDao.insert(new TipoServicio("Diseño de moda", true));
            tipoServicioDao.insert(new TipoServicio("Importar productos", true));
            tipoServicioDao.insert(new TipoServicio("Artista de maquillaje", true));
            tipoServicioDao.insert(new TipoServicio("Estilista", true));
            tipoServicioDao.insert(new TipoServicio("Vendedor de bocadillos y bebidas", true));
            tipoServicioDao.insert(new TipoServicio("Artista de voz en off", true));
            tipoServicioDao.insert(new TipoServicio("Cervecero", true));
            tipoServicioDao.insert(new TipoServicio("Vinificación", true));
            tipoServicioDao.insert(new TipoServicio("Vender huevos", true));
            tipoServicioDao.insert(new TipoServicio("Hornear pan", true));
            tipoServicioDao.insert(new TipoServicio("Crear paquetes de cuidado personal", true));
            tipoServicioDao.insert(new TipoServicio("Instructor", true));
            tipoServicioDao.insert(new TipoServicio("Host de Airbnb", true));
            tipoServicioDao.insert(new TipoServicio("Gestión de la propiedad", true));
            tipoServicioDao.insert(new TipoServicio("Servicio de lavandería / planchado", true));
            tipoServicioDao.insert(new TipoServicio("Servicio de modificación de la ropa", true));
            tipoServicioDao.insert(new TipoServicio("Artículos de limpieza", true));
            tipoServicioDao.insert(new TipoServicio("Consultor de citas en línea", true));
            tipoServicioDao.insert(new TipoServicio("Servicio de abastecimiento", true));
            tipoServicioDao.insert(new TipoServicio("Agencia de viajes", true));
            tipoServicioDao.insert(new TipoServicio("Fabricación de juguetes", true));
            tipoServicioDao.insert(new TipoServicio("Servicio de entrega de alimentos", true));

            return null;
        }
    }

}
