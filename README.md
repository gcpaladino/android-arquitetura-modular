## Projeto de template que fiz para o superapp Carrefour Brasil.

O template é um projeto de exemplo que apresenta uma abordagem moderna para o desenvolvimento de aplicativos [Android] (https://www.android.com/) usando o [Kotlin] (https://kotlinlang.org/) e a mais recente pilha tecnológica.

O objetivo do projeto é demonstrar as melhores práticas, fornecer um conjunto de diretrizes e apresentar as tecnologias modernas do Android.
arquitetura de aplicativos modular, escalável, sustentável e testável. Esse template pode parecer simples, mas
possui todos esses pequenos detalhes que definirão a base sólida do aplicativo a longo prazo para squads maiores e
gerenciamento de ciclo de vida de aplicativos duradouros.

## Índice

-   [Desenvolvimento](#/src/master#sesenvolvimento)
-   [Design](#/src/master#design)
-   [Arquitetura](#/src/master#arquitetura)
-   [Documentação](#/src/master#documentação)
-   [Pilha-de-tecnologia](#/src/master#pilha-de-tecnologia)
-   [Licença](#/src/master#licença)

## Desenvolvimento

### Code style

Para manter o estilo e a qualidade do código, são usadas as ferramentas de análise estática abaixo. Todos eles usam a configuração adequada e você os encontra no diretório raiz do projeto `. {ToolName}`.

| Ferramentas                                                   | Arquivos de Configurações                                                                       | Comandos Basicos             | Comandos Fixos               |
|---------------------------------------------------------|----------------------------------------------------------------------------------:|---------------------------|---------------------------|
| [detekt](https://github.com/arturbosch/detekt)          | [/.detekt](#/src/master/.detekt)     | `./gradlew detekt`        | -                         |
| [ktlint](https://github.com/pinterest/ktlint)           | -                                                                                 | `./gradlew ktlint`        | `./gradlew ktlintFormat`  |
| [spotless](https://github.com/diffplug/spotless)        | [/.spotless](#/src/master/.spotless) | `./gradlew spotlessCheck` | `./gradlew spotlessApply` |
| [lint](https://developer.android.com/studio/write/lint) | [/.lint](#/src/master/.lint)         | `./gradlew lint`          | -                         |

Todas essas ferramentas são integradas no [pre-commit git hook] (https://git-scm.com/book/en/v2/Customizing-Git-Git-Hooks), para
garanta que todas as análises e testes estáticos sejam aprovados antes que você possa confirmar suas alterações. Para ignorá-los para confirmação específica, adicione esta opção no seu comando git:

```properties
git commit --no-verify
```

Os hooks de pré-commit do git têm exatamente as mesmas verificações do [CircleCI] (https://circleci.com/) e são definidos neste [script] (# / blob / master / scripts / git-hooks / pre-commit.sh). Esta etapa garante que todas as confirmações cumpram as regras estabelecidas. No entanto, a integração contínua será finalmente validada de que as alterações estão corretas.

## Design

O aplicativo dar [suporte para diferentes tamanhos de tela] (https://developer.android.com/training/multiscreen/screensizes) e o conteúdo foi adaptado para caber em dispositivos móveis e tablets. Para isso, foi criado um layout flexível usando um ou mais dos seguintes conceitos:

-   [Use constraintLayout](https://developer.android.com/training/multiscreen/screensizes#ConstraintLayout)
-   [Evite tamanhos de layout codificados
](https://developer.android.com/training/multiscreen/screensizes#TaskUseWrapMatchPar)
-   [Crie layouts alternativos](https://developer.android.com/training/multiscreen/screensizes#alternative-layouts)
-   [Use o menor qualificador de largura](https://developer.android.com/training/multiscreen/screensizes#TaskUseSWQuali)
-   [Use o qualificador de largura disponível](https://developer.android.com/training/multiscreen/screensizes#available-width)
-   [Adicionar qualificadores de orientação](https://developer.android.com/training/multiscreen/screensizes#TaskUseOriQuali)

Em termos de design, foram seguidas as recomendações do [android material design] (https://developer.android.com/guide/topics/ui/look-and-feel) guia abrangente para design visual, de movimento e de interação entre plataformas e dispositivos . Concedendo ao projeto dessa maneira uma ótima experiência do usuário (UX) e interface do usuário (UI). Para obter mais informações sobre as práticas recomendadas do UX, acesse [link] (https://developer.android.com/topic/google-play-instant/best-practices/apps).

Além disso, foi implementado o suporte ao [tema escuro] (https://developer.android.com/guide/topics/ui/look-and-feel/darktheme) com os seguintes benefícios:
- Pode reduzir o uso de energia em uma quantidade significativa (dependendo da tecnologia da tela do dispositivo).
- Melhora a visibilidade para usuários com baixa visão e aqueles que são sensíveis à luz brilhante.
- Facilita a utilização de um dispositivo em um ambiente com pouca luz.

| Modo  | Characters list                                                          | Characters favorite                                                          | Character detail                                                          |
|-------|--------------------------------------------------------------------------|------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| Light | <img src="screenshots/phone/light_mode_characters_list.png" width="250"> | <img src="screenshots/phone/light_mode_characters_favorite.png" width="250"> | <img src="screenshots/phone/light_mode_character_detail.png" width="250"> |
| Dark  | <img src="screenshots/phone/dark_mode_characters_list.png" width="250">  | <img src="screenshots/phone/dark_mode_characters_favorite.png" width="250">  | <img src="screenshots/phone/dark_mode_character_detail.png" width="250">  |

## Arquitetura

A arquitetura do aplicativo basicamente aplica e cumpre rigorosamente cada um dos 5 pontos a seguir:

[img](h#/src/master/screenshots/architecture/project_structure.png)

-   Uma arquitetura single-activity , usando o [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) para gerenciar operações de fragments.
-   [Arquitetura de Componentes](https://developer.android.com/topic/libraries/architecture/), parte do Android Jetpack para dar ao projeto um design robusto, testável.
-   Padrão [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (MVVM) facilitando a [separação](https://en.wikipedia.org/wiki/Separation_of_concerns) do desenvolvimento da interface gráfica do usuário.
-   [S.O.L.I.D](https://en.wikipedia.org/wiki/SOLID) princípios de design destinados a tornar os projetos de software mais compreensíveis, flexíveis de manutenções.
-   [Arquitetura de Aplicativos modular](https://proandroiddev.com/build-a-modular-android-app-architecture-25342d99de82) permite que sejam desenvolvidos recursos isoladamente, independentemente de outros recursos.

### Módulos

Módulos são uma coleção de arquivos de origem e configurações de construção que permitem dividir um projeto em unidades discretas de funcionalidade. Nesse caso, além de dividir por funcionalidade / responsabilidade, existe a seguinte dependência entre eles:


O gráfico acima mostra a modularização do aplicativo:
-   `:app` depende de `:core` e indiretamente depende de `:features` via dynamic-features.
-   `:features` dependem de `:uiLayer`, `:dataLayer`, `:libraries` e `:app`.
-   `:dataLayer` e `:uiLayer` depende apenas de possíveis utilitários `:libraries`.
-   `:libraries` não possui dependencias.

#### Módulo App

O módulo `: app` é um [com.android.application] (https://developer.android.com/studio/build/), necessário para criar o pacote de aplicativos. Também é responsável por iniciar o [gráfico de dependência] (https://github.com/google/dagger), [play core] (https://developer.android.com/reference/com/google/android/play/ principais / notas de versão) e outras bibliotecas globais do projeto, diferenciando-se especialmente entre diferentes ambientes de aplicativos.


#### Módulo dataLayer

O módulo `: dataLayer` é uma [com.android.library] (https://developer.android.com/studio/projects/android-library) para atender requests a API ou acessar o banco de dados. Fornecendo a fonte de dados para os muitos recursos que a exigem.


#### Módulo Features

O módulo `: features` é um [com.android.dynamic-feature] (https://developer.android.com/studio/projects/dynamic-delivery) é essencialmente um módulo gradle que pode ser baixado independentemente do aplicativo base módulo. Ele pode conter código e recursos e incluir dependências, como qualquer outro módulo gradle.


#### Módulo uiLayer

Os módulos `: uiLayer` são uma [com.android.library] (https://developer.android.com/studio/projects/android-library) contém apenas código e recursos que são compartilhados entre os módulos de recursos. Reutilizando recursos, layouts, visualizações e componentes dessa maneira nos diferentes módulos de recursos, sem a necessidade de duplicar o código.



#### Módulo Libraries

Os módulos `: libraries` são uma [com.android.library] (https://developer.android.com/studio/projects/android-library), basicamente contém diferentes utilitários que podem ser usados pelos diferentes módulos.


### Arquitetura de Componentes

Idealmente os ViewModels não devem saber nada sobre o Android. Isso melhora a testabilidade, a segurança de vazamentos e a modularidade. Os modelos de exibição têm escopos diferentes das atividades ou fragmentos. Enquanto um ViewModel está ativo e em execução, uma atividade pode estar em qualquer um de seus estados de ciclo de vida. Atividades e fragmentos podem ser destruídos e criados novamente enquanto o ViewModel não está ciente.

Passar uma referência da View (atividade ou fragmento) ao ViewModel é um risco sério. Vamos supor que o ViewModel solicite dados da rede e os dados retornem algum tempo depois. Nesse momento, a referência de exibição pode ser destruída ou pode ser uma atividade antiga que não é mais visível, gerando um vazamento de memória e, possivelmente, uma falha.

[img](#/src/master/screenshots/architecture/diagram_communication_layers.png)

A comunicação entre as diferentes camadas segue o diagrama acima usando o paradigma reativo, observando alterações nos componentes sem a necessidade de retornos de chamada, evitando vazamentos e casos extremos relacionados a eles.

### Build variants

A aplicação possui diferentes tipos de produtos: `Dev`,` QA`, `Prod`. Cada variante possui um ambiente de destino específico e, para facilitar sua distinção, o aplicativo usa uma cor de ícone específica para a variante de compilação `debug` e` release` com o nome descritivo do aplicativo. Nesse caso, e como é uma amostra, todas as variantes têm o mesmo ponto de extremidade da API da Marvel.
Mas a idéia é ter diferentes ambientes de destino para desenvolvimento e controle de qualidade, respectivamente, o que não afeta o ambiente de produção. Isso é aplicável a qualquer ferramenta, plataforma, serviço que está sendo usado. Para mais informações sobre a variante de compilação, verifique este [link] (https://developer.android.com/studio/build/build-variants).

| Tipos   | DEV                                                                             | QA                                                                             | PROD                                                                         |
|---------|:-------------------------------------------------------------------------------:|:------------------------------------------------------------------------------:|:----------------------------------------------------------------------------:|
| Debug   | <p><img src="app/src/debug/res/mipmap-xhdpi/ic_launcher.png"><br> AppDEV</p> | <p><img src="app/src/debug/res/mipmap-xhdpi/ic_launcher.png"><br> CarrefuorQA</p> | <p><img src="app/src/debug/res/mipmap-xhdpi/ic_launcher.png"><br> Carrefuor</p> |
| Release | <p><img src="app/src/main/res/mipmap-xhdpi/ic_launcher.png"><br> CarrefuorDEV</p>  | <p><img src="app/src/main/res/mipmap-xhdpi/ic_launcher.png"><br> CarrefuorQA</p>  | <p><img src="app/src/main/res/mipmap-xhdpi/ic_launcher.png"><br> Carrefuor</p>  |

## Documentação

A documentação é gerada após a linguagem [KDoc] (https://kotlinlang.org/docs/reference/kotlin-doc.html) (o equivalente à linguagem [JavaDoc] de Java (https://en.wikipedia.org/wiki/Javadoc) )) através do mecanismo de documentação do Kotlin [Dokka] (https://github.com/Kotlin/dokka).

Para consultá-lo, verifique abra o diretório `/ docs` do projeto.

## Pilha-de-tecnologia

Este projeto tira proveito de muitas bibliotecas, plugins e ferramentas populares do ecossistema Android. A maioria das bibliotecas está na versão estável, a menos que haja um bom motivo para usar dependência não estável.

### Desenvolvimento

-   [Jetpack](https://developer.android.com/jetpack):
    -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - forneça Kotlin conciso e idiomático para APIs da plataforma Jetpack e Android.
    -   [AndroidX](https://developer.android.com/jetpack/androidx) - grande melhoria no Android [Support Library] original (https://developer.android.com/topic/libraries/support-library/index), que não é mais mantido.
    -   [Benchmark](https://developer.android.com/studio/profile/benchmark.html) - lida com o aquecimento, mede o desempenho do código e gera resultados de benchmarking no console do Android Studio.
    -   [Data Binding](https://developer.android.com/topic/libraries/data-binding/) - permite vincular componentes da interface do usuário em seus layouts a fontes de dados no seu aplicativo usando um formato declarativo em vez de programaticamente.
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - executar ações em resposta a uma alteração no status do ciclo de vida de outro componente, como activities e fragments.
    -   [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - com reconhecimento do ciclo de vida, o que significa que respeita o ciclo de vida de outros componentes do aplicativo, como activities, fragments ou services.
    -   [Navigation](https://developer.android.com/guide/navigation/) - ajuda a implementar a navegação, de cliques simples em botões a padrões mais complexos como app bars e navigation drawer.
    -   [Paging](https://developer.android.com/topic/libraries/architecture/paging/) - ajuda a carregar e exibir pequenos pedaços de dados por vez. Carregar dados parciais sob demanda reduz o uso da largura de banda da rede e dos recursos do sistema.
    -   [Room](https://developer.android.com/topic/libraries/architecture/room) - A biblioteca de persistência fornece uma camada de abstração sobre o SQLite para permitir um acesso mais robusto ao banco de dados, enquanto aproveita todo o poder do SQLite.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - projetado para armazenar e gerenciar dados relacionados à interface do usuário de maneira consciente do ciclo de vida. A classe ViewModel permite que os dados sobrevivam a alterações na configuração, como rotações de tela.
-   [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - gerenciar threads em segundo plano com código simplificado e reduzir as necessidades de callbacks.
-   [Dagger2](https://dagger.dev/) - injetor de dependência para substituir todas as classes FactoryFactory.
-   [Retrofit](https://square.github.io/retrofit/) - cliente HTTP para implementações de chamadas a API.
-   [Coil](https://github.com/bumptech/glide) - biblioteca de carregamento de imagens para Android, apoiada por Kotlin Coroutines.
-   [Moshi](https://github.com/square/moshi) -facilita o parse de JSON em objetos Kotlin.
-   [Timber](https://github.com/JakeWharton/timber) - um criador de logs com uma API pequena e extensível que fornece utilidade sobre a classe de log normal do Android.
-   [Stetho](http://facebook.github.io/stetho/) - ponte de depuração para aplicativos via Ferramentas de desenvolvedor do Chrome.

### Testes

-   [UIAutomator](https://developer.android.com/training/testing/ui-automator) - uma estrutura de teste de interface do usuário adequada para teste de interface do usuário funcional entre aplicativos em aplicativos instalados e no sistema.
-   [Espresso](https://developer.android.com/training/testing/espresso) - para escrever testes concisos, bonitos e confiáveis da interface do usuário do Android.
-   [Robolectric](https://github.com/robolectric/robolectric) - estrutura de teste unitarios padrão do setor para Android..
-   [JUnit](https://github.com/junit-team/junit4) - uma estrutura simples para escrever testes repetíveis. É uma instância da arquitetura xUnit para estruturas de teste unitarios.
-   [Mockito](https://github.com/nhaarman/mockito-kotlin) - estrutura de mock mais popular para testes unitarios escritos em Java.
-   [Mockk](https://github.com/mockk/mockk) - fornece DSL para simular comportamento. Construído a partir do zero para se ajustar à linguagem Kotlin.
-   [AndroidX](https://github.com/android/android-test) - a biblioteca de teste androidx fornece uma estrutura abrangente para testar aplicativos Android.

### Plugins

-   [Ktlint](https://github.com/pinterest/ktlint) - um lint Kotlin com formatador incorporado.
-   [Detekt](https://github.com/arturbosch/detekt) - uma ferramenta de análise de código estática para a linguagem de programação Kotlin.
-   [Spotless](https://github.com/diffplug/spotless) - um formatador de código pode fazer mais do que apenas encontrar erros de formatação.
-   [Versions](https://github.com/ben-manes/gradle-versions-plugin) - facilite a determinação de quais dependências possuem atualizações.
-   [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) - gera classes simples de objeto e construtor para navegação com segurança de tipo e acesso a quaisquer argumentos associados.
-   [Jacoco](https://github.com/jacoco/jacoco) - biblioteca de cobertura de código


### Projetos de referencias

Esses projetos foram usados como referencias para esse template do time de Android do Carrefuor.

-   [iosched](https://github.com/google/iosched) (by [google](https://github.com/google)) -aplicativo oficial para Android do google IO 2019.
-   [plaid](https://github.com/android/plaid) (by [android](https://github.com/android)) - aplicativo que fornece notícias e inspiração sobre design, sendo um exemplo de implementação do material design.
-   [sunflower](https://github.com/android/sunflower) (by [android](https://github.com/android)) - um aplicativo de jardinagem que ilustra as melhores práticas de desenvolvimento do Android com o Android Jetpack.
-   [architecture-components-samples](https://github.com/android/architecture-components-samples) (by [android](https://github.com/android)) - coleção de exemplos para componentes da arquitetura Android.
-   [architecture-sample](https://github.com/android/architecture-samples) (by [android](https://github.com/android)) - coleção de exemplos para discutir e mostrar diferentes ferramentas e padrões de arquitetura para aplicativos Android.
-   [android-clean-architecture-boilerplate](https://github.com/bufferapp/android-clean-architecture-boilerplate) (by [bufferapp](https://github.com/bufferapp)) - um projeto de clichê android usando arquitetura clean.
-   [android-kotlin-clean-architecture](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture) (by [sanogueralorenzo](https://github.com/sanogueralorenzo)) - exemplo de aplicativo usando arquitetura clean com Kotlin.
-   [modularization-example](https://github.com/JeroenMols/ModularizationExample) (by [JeroenMols](https://github.com/JeroenMols)) - exemplo fácil de um aplicativo Android modularizado.
-   [lego-catalog](https://github.com/Eli-Fox/LEGO-Catalog) (by [Eli-Fox](https://github.com/Eli-Fox)) - aplicativo que ilustra o estado atual da arquitetura do Android usando as melhores práticas de desenvolvimento do Android.
-   [tivi](https://github.com/chrisbanes/tivi) (by [chrisbanes](https://github.com/chrisbanes)) - um aplicativo que tenta usar as mais recentes bibliotecas e ferramentas de ponta.
-   [android-showcase](https://github.com/igorwojda/android-showcase) (by [igorwojda](https://github.com/igorwojda)) - aplicativo seguindo as práticas recomendadas: Kotlin, coroutines, Arquitetura clean, feature modules, testes, MVVM e análise estática.

### Artigos

Uma coleção de artigos muito interessantes relacionados às últimas tendências e recomendações da comunidade Android, para começar a levar em consideração noz projetoz atuais e próximos do Carrefuor:

-   [Transform monolith to modularization application](https://medium.com/androiddevelopers/a-patchwork-plaid-monolith-to-modularized-app-60235d9f212e)
-   [Using the Navigation Component in a Modular World](https://medium.com/swlh/using-the-navigation-component-in-a-modular-world-e7578825962)
-   [Dependency injection in a multi module project](https://medium.com/androiddevelopers/dependency-injection-in-a-multi-module-project-1a09511c14b7)
-   [ViewModels and LiveData: Patterns + AntiPatterns](https://medium.com/androiddevelopers/viewmodels-and-livedata-patterns-antipatterns-21efaef74a54)
-   [Dynamic feature and regular modules using Dagger2](https://blog.q42.nl/dynamic-feature-and-regular-modules-using-dagger2-12a7edcec1ff)
-   [Android Architecture starring Kotlin Coroutines, Jetpack (MVVM, Room, Paging), Retrofit and Dagger 2](https://proandroiddev.com/android-architecture-starring-kotlin-coroutines-jetpack-mvvm-room-paging-retrofit-and-dagger-7749b2bae5f7)
-   [Official Kotlin Style Guide with Ktlint](https://proandroiddev.com/official-kotlin-style-guide-with-ktlint-4a649c172956)
-   [Gradle dependency management with Kotlin (buildSrc)](https://proandroiddev.com/gradle-dependency-management-with-kotlin-94eed4df9a28)
-   [Detecting Kotlin Code Smells with Detekt](https://proandroiddev.com/detecting-kotlin-code-smells-with-detekt-e79c52a35faf)
-   [Best coding practices, tips and more for Android](https://medium.com/mindorks/best-coding-practices-tips-and-more-for-android-4ec03c7eeb2c)

### Bibliotecas

A comunidade de código aberto cria e mantém inúmeras bibliotecas incríveis, facilitando seu trabalho, dando a oportunidade de usá-las em seus desenvolvimentos. Aqui está uma coleção muito importante deles:

-   [awesome-android-ui](https://github.com/wasabeef/awesome-android-ui) - lista de coleções de impressionantes bibliotecas de UI / UX para Android.
-   [awesome-android-libraries](https://github.com/KotlinBy/awesome-kotlin#android-libraries) - coleção de coisas incríveis relacionadas ao Kotlin.
-   [android-arsenal](https://android-arsenal.com/) - portal do desenvolvedor Android com ferramentas, bibliotecas e aplicativos.

### Melhores Práticas

Evite reinventar a roda seguindo estas diretrizes:

-   [Google best practices](https://developer.android.com/distribute/best-practices)
-   [Android development best practices](https://github.com/futurice/android-best-practices)

### Codelabs

Os Codelabs do Google Developers oferecem uma experiência prática e orientada em codificação. A maioria dos codelabs o guiará pelo processo de criação de um aplicativo pequeno ou de adição de um novo recurso a um aplicativo existente. Eles abrangem uma ampla gama de conceitos do Android para aprender e praticar:

-   [Android Developer Fundamentals](https://developer.android.com/courses/fundamentals-training/toc-v2)
-   [Android Developer Codelabs](https://codelabs.developers.google.com/?cat=Android)

