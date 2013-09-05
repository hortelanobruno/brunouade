/* Nofication Close Buttons */
$('.notification a.close').click(function(e) {
    e.preventDefault();

    $(this).parent('.notification').fadeOut();
});

/*
 Check All Feature
 */
$(".check-all").click(function() {
    $("table input[type=checkbox]").attr('checked', $(this).is(':checked'));
});

/*
 Dropdowns
 */
$('.dropdown-toggle').dropdown();

/*
 Set focus on the first form field
 */
$(":input:visible:first").focus();

/*
 Responsive Navigation
 */
$('.collapse').collapse();

/*
 Prevent elements classed with "no-link" from linking
 */
//$(".no-link").click(function(e){ e.preventDefault();	});
function changeTiposTorneos() {
    document.getElementById("formtiposdetorneos").submit();
}


function sortearFixture() {
    //Obtengo equipos elegidos
    var e = document.getElementById("equipoelegidos");
    var options = e && e.options;
    var opt;
    var result = [];
    for (var i = 0, iLen = options.length; i < iLen; i++) {
        opt = options[i];

        if (opt.selected) {
            var data = new Array();
            data[0] = opt.value;
            data[1] = opt.text;
            result.push(data);
        }
    }

    //shuffle
    result = shuffle(result);

    //Obtengo categoria: 1 grupo 2 llave 3 grupo y llave
    var e = document.getElementById("administrar_torneos_categoria");
    var categoria = e.options[e.selectedIndex].value;
    //Sorteo fixture

    if (categoria == 1) {
        //liga
        var teams = result.length;
        var ghost = false;
        if (teams % 2 === 1) {
            teams++;
            ghost = true;
        }

        var totalRounds = teams - 1;
        var matchesPerRound = teams / 2;
        var rounds = new Array();
        var rounds2 = new Array();

        for (var round = 0; round < totalRounds; round++) {
            for (var match = 0; match < matchesPerRound; match++) {
                var home = (round + match) % (teams - 1);
                var away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                    rounds[round] = new Array();
                    rounds2[round] = new Array();
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
//                rounds[round][match] = (home + 1) + " v " + (away + 1);
                if (ghost) {
                    if ((home + 1) == teams) {
                        rounds[round][match] = "Queda libre " + result[away][1];
                        rounds2[round][match] = "Queda libre " + result[away][0];
                    } else if ((away + 1) == teams) {
                        rounds[round][match] = "Queda libre " + result[home][1];
                        rounds2[round][match] = "Queda libre " + result[home][0];
                    } else {
                        rounds[round][match] = result[home][1] + " vs " + result[away][1];
                        rounds2[round][match] = result[home][0] + " vs " + result[away][0];
                    }
                } else {
                    rounds[round][match] = result[home][1] + " vs " + result[away][1];
                    rounds2[round][match] = result[home][0] + " vs " + result[away][0];
                }
            }
        }

        // Interleave so that home and away games are fairly evenly dispersed.
        var interleaved = new Array();
        var interleaved2 = new Array();

        var evn = 0;
        var odd = (teams / 2);
        for (var i = 0; i < rounds.length; i++) {
            if (i % 2 == 0) {
                interleaved[i] = rounds[evn];
                interleaved2[i] = rounds2[evn];
                evn++;
            } else {
                interleaved[i] = rounds[odd];
                interleaved2[i] = rounds2[odd];
                odd++;
            }
        }

        rounds = interleaved;
        rounds2 = interleaved2;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (var round = 0; round < rounds.length; round++) {
            if (round % 2 == 1) {
                rounds[round][0] = flip(rounds[round][0]);
                rounds2[round][0] = flip(rounds2[round][0]);
            }
        }

        //rounds tiene el fixture y ghost tiene si el numero mas grande es partido libre

        var ronda;
        $('#tableFixture').empty();
        for (var i = 0; i < rounds.length; i++) {
            var tr = document.createElement('tr');
            var td = document.createElement('td');
            $(td).html("Ronda " + (i + 1));
            tr.appendChild(td);
            $('#tableFixture').append(tr);
            ronda = rounds[i];
            for (var j = 0; j < ronda.length; j++) {
                var tr = document.createElement('tr');
                var td = document.createElement('td');
                $(td).html("Partido: " + ronda[j].toString());
                tr.appendChild(td);
                $('#tableFixture').append(tr);
            }
        }

        $('#fixtureresult').val(rounds2.toString());
        $('#fixtureordenequipos').val(result.toString());
    }
}

function flip(match) {
    if (match.indexOf(" vs ") != -1) {
        var components = match.split(" vs ");
        return components[1] + " vs " + components[0];
    } else {
        return match;
    }
}

function shuffle(o) { //v1.0
    for (var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x)
        ;
    return o;
}