-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Mag 20, 2025 alle 09:56
-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `satira`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `img` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `admin`
--

INSERT INTO `admin` (`id`, `username`, `nickname`, `password`, `img`) VALUES
(1, 'Marco', 'VatiCane', 'vaticane1$', 'https://media.tio.ch/files/domains/tio.ch/images/4av4/s_-diego-abatantuono-x4iy.jpg'),
(2, 'GianMarco', 'Jimmy', 'supersecure', 'https://images-ext-1.discordapp.net/external/--288FlzIEIopzNq0ypNL2S3EX7-wqkV8GneOMwcPVo/https/i.pinimg.com/736x/4d/c5/4c/4dc54cd040dd544b7dcac3fd6a4da717.jpg?format=webp&width=1080&height=1080'),
(3, 'Tiziano', 'Nightmare', 'ammazzachemazza', 'https://cdn-idoli-a.facciabuco.com/273/cane-super-simpa/idolo.jpg?v=0'),
(4, 'Elena', 'The Queen', 'password', 'https://i0.wp.com/blog.cliomakeup.com/wp-content/uploads/2020/11/cliomakeup-regina-scacchi-8-capelli-.jpg?resize=696%2C695&ssl=1'),
(5, 'fiammetta', 'flaminem', 'javita142', 'https://i.pinimg.com/736x/41/c9/1b/41c91b09c53c60c6f07785211f107eb3.jpg');

-- --------------------------------------------------------

--
-- Struttura della tabella `commenti`
--

CREATE TABLE `commenti` (
  `id` int(11) NOT NULL,
  `testo` varchar(255) DEFAULT NULL,
  `immagine_commento` longtext DEFAULT NULL,
  `data_commento` datetime NOT NULL,
  `fk_id_post` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `immagine_post` longtext DEFAULT NULL,
  `titolo` text NOT NULL,
  `contenuto` text DEFAULT NULL,
  `data_pubblicazione` datetime NOT NULL,
  `fk_id_admin` int(11) NOT NULL,
  `visible` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `post`
--

INSERT INTO `post` (`id`, `immagine_post`, `titolo`, `contenuto`, `data_pubblicazione`, `fk_id_admin`, `visible`) VALUES
(1, 'claudio-bonanni-WgLjj6ZYhV0-unsplash.jpg', 'Papa Francesco sostituisce la Fiat 500 con un Tesla papi-mobile: \'Finalmente silenzio durante le preghiere\'', 'CITTA’ DEL VATICANO – In una mossa storica per modernizzare la Santa Sede, Papa Francesco ha detto addio alla mitica Fiat 500 bianca e ha svelato il nuovo “papi-mobile” elettrico: una Tesla Model X personalizzata con finestrini antiproiettile e un impianto stereo che trasmette esclusivamente cori gregoriani.\n\n\"Finalmente posso pregare senza sentire il rumore del motore che sembra un demonio in agonia\", ha scherzato il Pontefice durante la benedizione dell’auto, prima di aggiungere: \"Però Elon Musk mi ha promesso che non si trasformerà in un’arma autonoma. Speriamo sia un miracolo.\"\n\nFonti vicine al Vaticano rivelano che la Tesla ha anche una funzione “Autopilot per miracoli”, che teoricamente potrebbe guidare da sola verso il luogo di un’apparizione mariana. \"Ma per ora la useremo solo per evitare il traffico dopo l’Angelus\", ha precisato un monsignore.', '2025-05-19 17:02:46', 5, 1),
(2, 'engin-akyurt-hPIYvFq_t3o-unsplash.jpg', 'Scoperto che il Vaticano usa il Tinder degli angeli: \'Swipe right per un miracolo\'', 'ROMA – Un’inchiesta shock del Corriere della Sera ha rivelato che la Chiesa cattolica utilizza da anni un’app segreta chiamata AngelMatch, una sorta di Tinder soprannaturale dove i santi e gli angeli vengono “swipati” per ottenere grazie e interventi divini.\n\n\"Funziona così: se hai bisogno di un miracolo, scorri il profilo di un santo e se c’è match, la richiesta viene approvata\", spiega frate Luca, esperto di angelologia 2.0. \"San Giuseppe è quello con più like, ma Santa Rita è la più veloce a rispondere. San Pietro invece ti lascia sempre in ‘attesa’.\"\n\nSecondo alcuni, l’algoritmo sarebbe pilotato: \"Per qualche motivo, le preghiere di chi dona il 5x1000 alla Chiesa hanno priorità assoluta\", sussurra un insider. Intanto, il Vaticano smentisce: \"È solo un tool di spiritualità digitale. E no, il Diavolo non è su Bumble.\"', '2025-05-19 17:02:46', 3, 1),
(3, 'marcin-jozwiak-oh0DITWoHi4-unsplash.jpg', 'Nuova enciclica shock: \'Il Wi-Fi è un dono divino, ma il 5G è opera del Demonio\'', 'VATICANO – Con un documento che farà discutere, Papa Francesco ha firmato l’enciclica \"Digitale Fidei\", in cui definisce il Wi-Fi \"un dono della Provvidenza\", ma condanna senza appello il 5G come \"strumento di tentazione tecnologica\".\n\n*\"Il Wi-Fi unisce le persone, il 5G le trasforma in cavie di un esperimento globale\"*, si legge nel testo, prima di aggiungere: \"E no, Satana non è il CEO di TIM. Però ci ha pensato su.\"\n\nLa notizia ha scatenato polemiche: i giovani cattolici protestano (\"Come faccio a seguire la messa in streaming senza 5G?\"), mentre i complottisti esultano: \"Lo avevamo detto! Le antenne sono le corna del Male!\" Intanto, il Papa ha lanciato un’offerta speciale: \"Acquista ora la nostra rete ‘Holy-Fi’: velocità divina, zero buffer durante i miracoli.\"', '2025-05-19 17:02:46', 2, 1),
(4, 'moritz-kindler-gOqBe7ropxM-unsplash.jpg', 'Papa Francesco apre un profilo OnlyFans: Solo contenuti spirituali (e piedi santi)', 'In un audace tentativo di modernizzare l\'evangelizzazione, Papa Francesco ha stupito il mondo aprendo un profilo su OnlyFans. L\'account, intitolato \"Santo Subito (ma pagate l\'abbonamento)\", promette contenuti spirituali esclusivi per i fedeli digitali. \"Non è quello che pensate\", ha chiarito il Pontefice in un video introduttivo. \"Qui troverete meditazioni profonde, riflessioni teologiche e, sì, anche qualche primo piano dei miei piedi, in ricordo della lavanda dei piedi. Tutto nel massimo rispetto della tradizione\".\n\nI contenuti premium includono sessioni di \"Confessioni Express\", dove per pochi euro in più è possibile chiedere consigli spirituali direttamente al Papa. C\'è poi \"Il Vangelo in Pigiama\", una serie intima di letture serali con Francesco in vestaglia e pantofole, e \"Miracoli in Diretta\", appuntamento settimanale in cui il Papa tenta di spostare oggetti con la sola forza della preghiera. \"Se non funziona, almeno ci siamo divertiti\", scherza tra i fedeli.\n\nLa risposta è stata travolgente: migliaia di iscritti nella prima ora, tra cui curiosi, devoti e qualche cardinale sotto pseudonimo. Le reazioni vanno dall\'entusiasmo (\"Finalmente un Papa che ci capisce!\") allo scandalo (\"Ma questo è il mercato del tempio!\"). Intanto, l\'hashtag #PapaFrancescoFeet sta spopolando sui social, dimostrando che, miracolo o no, il marketing divino funziona.', '2025-05-19 17:02:46', 4, 1),
(5, 'nuno-marques-0GbrjL3vZF4-unsplash.jpg', 'Conclave annunciato su eBay: \'Offrite pure, ma niente stregonerie\'', 'ROMA – Dopo il sorprendente successo delle vendite online di reliquie autentiche - tra cui il celebre annuncio \"Un dito di San Antonio, mai usato!\" - il Vaticano ha deciso di portare l\'innovazione digitale anche nell\'elezione del Pontefice. Il prossimo Conclave si terrà infatti su eBay, con aste in tempo reale che promettono di rendere il processo più trasparente, competitivo e... imprevedibile.\n\nL\'annuncio ufficiale, pubblicato nella sezione \"Arte e Collezionismo\" del sito, recita: \"Starting bid: 1 milione di euro. No reserve, ma accettiamo anche offerte in indulgenze plenarie\". Le regole sono chiare: niente magia nera (anche se i fumetti bianchi sono tollerati), un feedback minimo del 98% per evitare antipapi recidivi, e spedizione prioritaria perché, come specifica il comunicato, \"lo Spirito Santo non aspetta\".\n\nTra i favoriti spiccano il cardinale @BenedettoFan69, con un feedback stellare e recensioni entusiastiche come \"Ottimo candidato, consegna veloce delle benedizioni\", e l\'outsider \"XxFranciscoLoverxX\", che attira offerte con l\'allettante omaggio di \"una coroncina del rosario fatta a mano dalle suore carmelitane\".\n\nMa c\'è una clausola che fa tremare i partecipanti: \"Se il pagamento salta, il conclave viene annullato e il Papa lo sceglie Amazon\". Un rischio che nessun cardinale vuole correre, considerando le politiche di reso del colosso americano. Intanto, gli osservatori si chiedono se questa sia l\'evoluzione naturale della Chiesa digitale o l\'ultimo passo prima del \"Papa Prime\", con consegna in un giorno e diritto alla restituzione entro 30 giorni.', '2025-05-19 17:02:46', 1, 1),
(6, 'nuno-marques-KV5U3XaGWao-unsplash.jpg', 'Vaticano lancia il Papal-Bon: fedeltà premiata e sconti sulla salvezza eterna', 'VATICANO – Dopo il clamoroso fallimento delle \"Ostie NFT\", la Santa Sede ha deciso di puntare su un programma fedeltà più tradizionale, ma non meno controverso: il Papal-Bon, la carta che rivoluziona il rapporto tra peccato e sconto.\n\n\"Funziona così: più peccati confessi, più vantaggi ottieni\", spiega suor Maria, cassiera del Divine Store, il nuovo punto vendita alle porte del Vaticano. Tra i benefit, spiccano il 10% di sconto sulle benedizioni ufficiali (con possibilità di estensione a parenti fino al secondo grado) e il 2x1 sulle messe domenicali, valido però solo per peccatori seriali (\"Almeno tre peccati mortali a settimana\", precisa il regolamento).\n\nMa è la raccolta punti a fare discutere: ogni acquisto di sacramenti o oggetti sacri accumula crediti per ridurre la permanenza in Purgatorio. \"Mille punti equivalgono a un’ora di espiazione in meno\", si legge nel volantino promozionale. E per i clienti più fedeli, c’è anche il cashback spirituale: \"Se rinneghi Satana durante la spesa, ti accredito il 5% della grazia direttamente sul conto corrente dell’anima\".\n\nLe critiche non si sono fatte attendere. \"La grazia divina non è una promozione da supermercato!\", tuonano i teologi più tradizionalisti. Ma Papa Francesco replica con una battuta: \"Gesù moltiplicò i pani e i pesci, noi moltiplichiamo le offerte. È quasi lo stesso\".\n\nIntanto, tra i fedeli serpeggia il malcontento di chi cerca di barare sul sistema. \"Ho confessato lo stesso peccato venti volte, cambiando solo il tono della voce\", ammette un parrocchiano sotto pseudonimo. \"Speriamo che in Cielo non abbiano un database aggiornato\".\n\nMentre il Vaticano studia una versione Platinum del Papal-Bon (con accesso prioritario ai miracoli e salta-coda per le canonizzazioni), il dibattito resta aperto: fino a che punto la fede può essere \"a punti\"? Per ora, l’unica certezza è che il Purgatorio, almeno per i possessori della carta, sarà un po’ meno lungo.', '2025-05-19 17:02:46', 3, 1),
(7, 'riccardo-annandale-7e2pe9wjL9M-unsplash.jpg', 'Papa Francesco contro i puristi del latino: \"Asterix vi aspetta a Roma!\"', 'VATICANO – Con la schiettezza che lo caratterizza, Papa Francesco ha preso posizione sul dibattito della messa in latino durante un\'intervista a Radio Vaticana. \"Se vi piace tanto parlare come Giulio Cesare, iscrivetevi a un corso universitario o andate a fare le comparse nei film su Roma antica\", ha ironizzato il Pontefice. \"La Chiesa è un organismo vivente, mica un museo archeologico!\"\n\nLa provocazione ha scatenato un putiferio. I tradizionalisti lefebvriani hanno risposto su Twitter con un lapidario \"Papa, tu es scandalum!\", mentre gli studenti di lettere classiche hanno trovato un inaspettato alleato: \"Finalmente qualcuno che ammette che il latino serve più a leggere Catullo che a pregare\", ha commentato un dottorando della Sapienza.\n\nNon è mancato il tocco dello spettacolo: Roberto Benigni, noto per le sue esibizioni in \"latino maccheronico\", si è offerto volontario per tenere lezioni al Papa. \"Gli insegnerò a declinare amicizia e fraternità come facevano i vecchi romani, ma con più gesti teatrali!\"\n\nDi fronte alle polemiche, il Vaticano ha trovato un compromesso salomonico: d\'ora in poi le messe in latino saranno permesse, ma solo se celebrate in stile rap. \"Credo in unum Deum, y\'all!\" è già diventato il tormentone delle parrocchie più trendy. Intanto, a Roma i negozi di costumi storici segnalano un insolito picco di richieste da parte di gruppi di fedeli indignati. Chissà se cercano toghe per protestare o per reinventarsi come gladiatori della tradizione.', '2025-05-19 17:02:46', 5, 1),
(8, 'science-in-hd-ZT5v0puBjZI-unsplash.jpg', 'Scisma digitale: i cardinali tradizionalisti lanciano \"OnlyPopes\"', 'ROMA – La Chiesa cattolica affronta la sua più bizzarra crisi dallo Scisma d\'Occidente: un gruppo di cardinali tradizionalisti, guidati dall\'irriducibile cardinale Burke, ha abbandonato Roma per lanciare \"OnlyPopes - Solo Papi in Latina\", una piattaforma in stile OnlyFans dedicata alla tradizione più intransigente.\n\n\"Finalmente un luogo incontaminato dal modernismo!\", ha dichiarato Burke in un video di presentazione girato davanti a un altare barocco, indossando una cappa magna che avrebbe fatto invidia a Pio V. La piattaforma promette messe in latino trasmesse 24 ore su 24, con particolare attenzione alle rubriche \"Vangelo con sottotitoli per millennials\" e \"Come indossare la tiara papale senza rompersi il collo\".\n\nL\'offerta include anche contenuti premium: corsi avanzati di gestualità benedicente (\"Il giusto polso per l\'asperges\"), live esorcistici con chat interattiva (\"Scrivi \'vade retro\' per far aumentare il volume all\'acqua santa\") e podcast sul Sillabo commentati in diretta. L\'unico intoppo? Il sistema di pagamento, che accetta solo fiorini fiorentini del XIV secolo. \"Abbiamo provato con PayPal, ma ci hanno bannati per \'attività sospette\'\", ha ammesso un portavoce.\n\nLa risposta del Vaticano è stata glaciale: \"Speriamo tornino prima di finire i soldi per comprare l\'incenso\". Intanto, fonti vicine alla Curia rivelano che Francesco avrebbe commentato la notizia con un \"Magari ci abbonano pure loro a \'OnlyFrati\', la nostra piattaforma per la vita comunitaria\".\n\nMentre i teologi si interrogano se un abbonamento mensile possa valere come indulgenza plenaria, il mondo cattolico si divide tra chi teme una nuova guerra delle investiture digitale e chi già prenota il merchandising ufficiale: borracce termiche con stampato \"Extra Ecclesiam nulla salus... ma il premium plan sì\".\n\"Finalmente un luogo incontaminato dal modernismo!\", ha dichiarato Burke in un video di presentazione girato davanti a un altare barocco, indossando una cappa magna che avrebbe fatto invidia a Pio V. La piattaforma promette messe in latino trasmesse 24 ore su 24, con particolare attenzione alle rubriche \"Vangelo con sottotitoli per millennials\" e \"Come indossare la tiara papale senza rompersi il collo\".\n\nL\'offerta include anche contenuti premium: corsi avanzati di gestualità benedicente (\"Il giusto polso per l\'asperges\"), live esorcistici con chat interattiva (\"Scrivi \'vade retro\' per far aumentare il volume all\'acqua santa\") e podcast sul Sillabo commentati in diretta. L\'unico intoppo? Il sistema di pagamento, che accetta solo fiorini fiorentini del XIV secolo. \"Abbiamo provato con PayPal, ma ci hanno bannati per \'attività sospette\'\", ha ammesso un portavoce.\n\nLa risposta del Vaticano è stata glaciale: \"Speriamo tornino prima di finire i soldi per comprare l\'incenso\". Intanto, fonti vicine alla Curia rivelano che Francesco avrebbe commentato la notizia con un \"Magari ci abbonano pure loro a \'OnlyFrati\', la nostra piattaforma per la vita comunitaria\".\n\nMentre i teologi si interrogano se un abbonamento mensile possa valere come indulgenza plenaria, il mondo cattolico si divide tra chi teme una nuova guerra delle investiture digitale e chi già prenota il merchandising ufficiale: borracce termiche con stampato \"Extra Ecclesiam nulla salus... ma il premium plan sì\".', '2025-05-19 17:02:46', 4, 1),
(9, 'sigmund-4UGmm3WRUoQ-unsplash.jpg', 'Nuovo miracolo riconosciuto: ‘Ha trasformato l’acqua in vino… ma era solo un sommelier ubriaco’', 'ASSISI – Dopo mesi di indagini, la Congregazione per le Cause dei Santi ha dismesso un presunto miracolo: \"Il ‘vino divino’ era solo un esperto enologo che aveva bevuto troppo prima di benedire la fonte\", ha spiegato un portavoce.\n\nIl protagonista, Marco di Cantina, si è giustificato: \"Era un venerdì sera, la luce era bassa… e forse ho confuso l’acqua con la mia borraccia di Barolo.\"\n\nNonostante tutto, i fedeli sul luogo giurano: \"Era buonissimo, quindi miracolo o no, grazie lo stesso!\"\nIl protagonista, Marco di Cantina, si è giustificato: \"Era un venerdì sera, la luce era bassa… e forse ho confuso l’acqua con la mia borraccia di Barolo.\"\n\nNonostante tutto, i fedeli sul luogo giurano: \"Era buonissimo, quindi miracolo o no, grazie lo stesso!\"', '2025-05-19 17:02:46', 2, 1),
(10, 'sven-mieke-fteR0e2BzKo-unsplash.jpg', 'Papa Francesco rivela la sua squadra Pokémon perfetta: \"Arceus è Dio, punto\"', 'VATICANO - In un momento di svago durante un\'udienza con youtuber cattolici, Papa Bergoglio ha stupito tutti rivelando la sua perfetta squadra Pokémon. \"Arceus è ovviamente il mio preferito - ha spiegato con un sorriso - È letteralmente Dio, l\'Alpha e l\'Omega del Pokédex\".\n\nLa squadra ideale del Pontefice non poteva che essere teologicamente coerente: Blissey, per la sua capacità di guarire (\"Come un buon confessore che risana le ferite dell\'anima\"), e Machamp, scelto per le sue quattro braccia (\"Per benedire il doppio dei fedeli nello stesso tempo\").\n\nTra le battute migliori, la confessione di aver tentato senza successo di catturare un Demonio di Galar: \"Era troppo potente, forse serviva un esorcismo anziché una Poké Ball\".\n\nLa rivelazione ha scatenato il web: i meme spaziano da \"Team Rocket che ruba le ostie\" a dibattiti su quale sia il vero Pokémon leggendario (\"Lo Spirito Santo, ovvio!\"). Intanto la Nintendo, sempre pronta a cogliere le opportunità, ha già annunciato un\'edizione speciale \"Vaticano\" con Pikachu in tonaca e una palestra guidata da un leader somigliante a San Pietro.\n\n\"Finalmente un modo per evangelizzare le nuove generazioni\", ha commentato un cardinale sotto pseudonimo, mentre i catechisti si chiedono se inserire il Pokédex tra i testi complementari per la preparazione alla Cresima.\nLa squadra ideale del Pontefice non poteva che essere teologicamente coerente: Blissey, per la sua capacità di guarire (\"Come un buon confessore che risana le ferite dell\'anima\"), e Machamp, scelto per le sue quattro braccia (\"Per benedire il doppio dei fedeli nello stesso tempo\").\n\nTra le battute migliori, la confessione di aver tentato senza successo di catturare un Demonio di Galar: \"Era troppo potente, forse serviva un esorcismo anziché una Poké Ball\".\n\nLa rivelazione ha scatenato il web: i meme spaziano da \"Team Rocket che ruba le ostie\" a dibattiti su quale sia il vero Pokémon leggendario (\"Lo Spirito Santo, ovvio!\"). Intanto la Nintendo, sempre pronta a cogliere le opportunità, ha già annunciato un\'edizione speciale \"Vaticano\" con Pikachu in tonaca e una palestra guidata da un leader somigliante a San Pietro.\n\n\"Finalmente un modo per evangelizzare le nuove generazioni\", ha commentato un cardinale sotto pseudonimo, mentre i catechisti si chiedono se inserire il Pokédex tra i testi complementari per la preparazione alla Cresima.', '2025-05-19 17:02:46', 1, 1),
(11, 'testing-TzjMd7i5WQI-unsplash.jpg', 'Vaticano Studios scommette sul cinema: annunciato \"La Passione 2\" con effetti speciali', 'HOLLYWOOD – Dopo il successo di \"Jesus Christ Superstar\", il Vaticano ha stretto un accordo da milioni di denari con la Lionsgate per produrre \"La Passione 2: Resurrection Boogaloo\", un kolossal che promette di rivoluzionare il genere biblico.\n\nLa scelta del cast ha già fatto discutere: Dwayne \"The Rock\" Johnson interpreterà un Cristo muscoloso (\"Finalmente un Gesù che può spaccare la pietra del sepolcro a mani nude!\"), mentre Giuda sarà trasformato in un complesso antieroe dark, con tanto di spin-off in lavorazione dal titolo provvisorio \"Il Traditore: Una storia di soldi e rimorsi\".\n\nLa scena più attesa? Il post-credit che anticipa il prossimo capitolo: \"San Paolo vs. Impero Romano - Dawn of Christianity\", con cameo di Costantino nei panni del deus ex machina.\n\nLe polemiche non si sono fatte attendere. \"La Resurrezione è già perfetta così com\'è!\", protestano i teologi più conservatori. Ma il regista Michael Bay, chiamato a dirigere l\'opera, replica senza mezzi termini: \"Sì, ma mancavano le esplosioni. E poi, chi non vuole vedere gli angeli trasformarsi in carri armati di luce?\".\n\nIntanto, in Vaticano c\'è chi sussurra che questa sia solo la prima di una lunga serie: già in cantiere \"Gli Apostoli - Endgame\" e \"Noè - Il primo influencer\", con protagonista un Noah 2.0 che twitta gli aggiornamenti sul Diluvio in tempo reale.', '2025-05-19 17:02:46', 4, 1),
(12, 'vivint-solar-ZEiFiOsV3K4-unsplash.jpg', 'Francesco deluso dal Metaverso: ‘Non riesco a benedire gente che non esiste’', 'SILICON VALLEY – Dopo un test su Meta Horizon, Papa Francesco ha ammesso: \"Ho provato a dare l’assoluzione a un NFT, ma non so se vale.\"\n\nIl problema? \"I fedeli virtuali chiedono miracoli digitali, tipo ‘trasforma il mio Bitcoin in Ethereum’\", ha spiegato. Per ora, il Vaticano sconsiglia: \"Meglio un rosario vero che uno in pixel.\"\n\nIntanto, Mark Zuckerberg ha proposto un \"Vaticano VR\", con cappella Sistina in 8K e Papa-bot per confessioni automatiche. \"No grazie\", la risposta secca. \"Il Paradiso non ha lag.\"SILICON VALLEY – Dopo un test su Meta Horizon, Papa Francesco ha ammesso: \"Ho provato a dare l’assoluzione a un NFT, ma non so se vale.\"\n\nIl problema? \"I fedeli virtuali chiedono miracoli digitali, tipo ‘trasforma il mio Bitcoin in Ethereum’\", ha spiegato. Per ora, il Vaticano sconsiglia: \"Meglio un rosario vero che uno in pixel.\"\n\nIntanto, Mark Zuckerberg ha proposto un \"Vaticano VR\", con cappella Sistina in 8K e Papa-bot per confessioni automatiche. \"No grazie\", la risposta secca. \"Il Paradiso non ha lag.\"', '2025-05-19 17:02:46', 5, 1),
(13, 'vivint-solar-zZOS5iQfVZ4-unsplash.jpg', 'Scandalo in Vaticano: Scoperto che San Pietro aveva il bancomat', 'GERUSALEMME – Durante gli scavi sotto la Basilica, gli archeologi hanno trovato una tavoletta d’argilla con inciso: \"Deposito 30 denari per la chiavi del Paradiso – Pagamento contactless accettato\".\n\n\"Pietro non era solo il primo Papa, ma anche il primo a monetizzare la fede\", scherza uno studioso. La Chiesa ha minimizzato: \"Erano offerte volontarie… più o meno.\"\n\nIntanto, Bitcoin è crollato dopo la notizia: \"Se anche il Cielo fa cash grab, allora non c’è speranza.\"', '2025-05-19 17:02:46', 1, 1),
(14, 'william-hook-9e9PD9blAto-unsplash.jpg', 'Papa Francesco a Elon Musk: ‘Se colonizzi Marte, ci vuole una diocesi. E paga le tasse’', 'NEW YORK – Dopo l’annuncio di SpaceX su Marte, il Pontefice ha twittato: \"@elonmusk, se fondi una città là, mettici almeno una chiesetta. E niente messe in streaming, vogliamo la presenza fisica.\"\n\nMusk ha risposto con un meme: \"Ok, ma la chiamerò ‘Our Father Who Art in Space’.\"\n\nLa NASA intanto studia come battezzare gli alieni: \"Se non hanno l’anima, almeno un’aspersione veloce?\"', '2025-05-19 17:02:46', 2, 1),
(15, 'zbynek-burival-V4ZYJZJ3W4M-unsplash.jpg', 'Nuovo dogma: ‘Il gelato alla vaniglia è l’unico accettato dal Cielo. Il cioccolato? Peccato veniale’', 'VATICANO – Con un motu proprio, il Papa ha stabilito che solo il gelato alla vaniglia è \"moralmente puro\". \"Il cioccolato è troppo tentatore, il pistacchio è anarchia, e il fior di latte… beh, quello è quasi un miracolo.\"\n\nI gelatai italiani sono in rivolta: \"E il stracciatella? È letteralmente il Paradiso in bocca!\" Ma il Vaticano non transige: \"Troppi peccati di gola. A meno che non sia benedetto da un sacerdote.\"\n\nIntanto, Grom ha lanciato il \"Gelato Penitenziale\": \"Mangiarlo conta come un’ora di purgatorio in meno!\"', '2025-05-19 17:02:46', 4, 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `nikname` (`nickname`);

--
-- Indici per le tabelle `commenti`
--
ALTER TABLE `commenti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_post` (`fk_id_post`);

--
-- Indici per le tabelle `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_admin` (`fk_id_admin`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `commenti`
--
ALTER TABLE `commenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `commenti`
--
ALTER TABLE `commenti`
  ADD CONSTRAINT `commenti_ibfk_1` FOREIGN KEY (`fk_id_post`) REFERENCES `post` (`id`);

--
-- Limiti per la tabella `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`fk_id_admin`) REFERENCES `admin` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
