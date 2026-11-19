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

## Disclaimer sobre el código

Esta aplicación nace de poco más que un *zero-shot* con GPT-6 Luna, usando OpenCode como *harness*. Es intencionado y no lo escondo: no afiné las instrucciones más allá de pedirle que construyera el ejercicio a partir de la idea que tenía y de la jerarquía de clases.

Para una o dos pantallas, esta organización puede ser tolerable, pero escala mal, aunque la estructura de paquetes ya existiera antes del prompt. Los modelos *baseline*, como GPT-6 Luna, son una *commodity* y lo más normal es que tengáis barra libre para usarlos cuando no haya datos sensibles. Por eso es importante desarrollar buenas prácticas para orientar el trabajo hacia soluciones que puedan escalar.

Reorganizar esta aplicación y mejorar su estructura también puede considerarse parte del ejercicio ya que trabaja una competencia al alza.
