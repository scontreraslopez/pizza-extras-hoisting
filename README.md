# Pizza Extras Hoisting

Miniaplicación didáctica en Jetpack Compose para practicar la **elevación de estado** cuando hay colecciones anidadas:

```text
Pedido
└── 1..N pizzas
    └── extras seleccionados para esa pizza
```

La aplicación carga un pedido dummy al azar. Cada pizza comparte el mismo catálogo de extras, pero las selecciones son independientes. El precio de cada pizza y el total del pedido se calculan a partir de esas selecciones. Al pagar, se muestra un `Toast` y se carga otro pedido.

## Objetivo didáctico

Seguir el recorrido del estado y de los eventos entre composables:

```text
OrderScreen → PizzaCard → ExtraRow
     ↑             ↑            │
     └──────── callbacks ────────┘
```

- `OrderScreen` es el dueño del pedido actual y de las selecciones.
- `PizzaCard` y `ExtraRow` reciben estado como parámetros. Los callbacks de selección están marcados con `/* TODO */` para que el alumnado implemente la propagación del evento y la actualización del estado.
- Las selecciones se indexan por id de pizza; así, marcar un extra en una pizza no lo marca en las demás.
- Los precios se derivan del estado seleccionado; no se almacenan como estado independiente.
- No se utiliza ViewModel ni persistencia.

## Estructura

- `app/src/main/java/net/iessochoa/sergiocontreras/pizzaextrashoisting/MainActivity.kt`: actividad, pantalla del pedido y composables.
- `app/src/main/java/net/iessochoa/sergiocontreras/pizzaextrashoisting/DummyData.kt`: modelos y singleton con catálogo de extras y pedidos inmutables de ejemplo.

## Ejecutar

Abre el proyecto en Android Studio y sincroniza Gradle. Se necesita un JDK compatible con la versión de Android Gradle Plugin del proyecto y la plataforma Android SDK 37.

También puedes compilar desde la raíz del proyecto:

```bash
./gradlew :app:assembleDebug
```

En Windows:

```bat
gradlew.bat :app:assembleDebug
```
