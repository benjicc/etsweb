/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    contador =1;
    conteo.value=contador;
    function readFile(input) {
        console.log("SASASSSSSS ",document.getElementById("file-upload").value);
        predoc=document.getElementById('file-preview');
        predoc.src="";
        if (input.files && input.files[0]) {
            var reader = new FileReader();
 
            reader.onload = function (e) {
                //e.target.result contents the base64 data from the image uploaded
                predoc.src = e.target.result;
                
                console.log(e.target.result);
                archivo=document.getElementById("divFileUpload");
                var previewZone = document.getElementById('file-preview-zone');
                previewZone.appendChild(predoc);
                document.getElementById('path').value=document.getElementById("file-upload").value
            }
            console.log(input.files[0]);
            reader.readAsDataURL(input.files[0]);
        }
    }
 
    var fileUpload = document.getElementById('file-upload');
    fileUpload.onchange = function (e) {
        readFile(e.srcElement);
    }
 

    dict = {};
    $(document).ready(function() {
    cords = [];
    cordsr = [];
    $("img").on("click", function(event) {
        
        
        var radio = $('input[name="activo"]:checked').val();
        console.log(radio)
        if($("#tipo"+radio.toString()+"").val()=="circle"){
        console.log("1111111111111111111111111111111111111")
        var cord = "";
        var x = event.pageX - this.offsetLeft;
        var y = event.pageY - this.offsetTop;
        cords.push(x);
        cords.push(y);
        cord =cords[0]+","+cords[1]+","+cords[2]+","+cords[3];
        dict[radio]=cord
    }else if($("#tipo"+radio.toString()+"").val()=="rect"){
        var x = event.pageX - this.offsetLeft;
        
        var y = event.pageY - this.offsetTop;
        cordsr.push(x);
        cordsr.push(y);
        cord =cordsr[0]+","+cordsr[1]+","+cordsr[2]+","+cordsr[3];
        dict[radio]=cord
    }
    $("#coordenadas"+radio.toString()+"").val(dict[radio]);
    console.log(dict)
    if (cords.length >= 4 ){
        cords=[];
        cord="";
    }
    if (cordsr.length >= 4 ){
        cordsr=[];
        cord="";
    }
    });
});

    function initmapsect(contador){

        texto="<tr id='fila"+contador.toString()+"'><td><input type='radio' value="+contador+" checked id='activo"+contador.toString()+"' name='activo'></td><td><select name='tipo"+contador.toString()+"' id='tipo"+contador.toString()+"'><option value='circle'>Circulo</option><option value='rect'>Rectangulo</option></select></td><td><select name='link"+contador.toString()+"' id='link"+contador.toString()+"'><option value='correcto'>Correcta</option><option value='incorrecto'>Incorrecta</option></select></td><td><input id='title"+contador.toString()+"' type='text' class='form-control' name='title"+contador.toString()+"'></td><td><input type='text' id='coordenadas"+contador.toString()+"' class='form-control' name='coordenadas"+contador.toString()+"'></td><td><span class='input-group-addon'><button id ='button"+contador.toString()+"' name='buttton"+contador.toString()+"' class='btn btn-primary  btn-circle' type='button' onclick='Nuevasect("+contador+");'><i id ='id_icono"+contador.toString()+"' class='fa fa-plus'></i></button></span></td></tr>";
        $('tbody').append(texto);
    }

    function Nuevasect(contador){
        var conteo = document.getElementById("conteo");
        
        contador = parseInt(conteo.value) + 1;
        conteo.value=contador;
        texto="<tr id='fila"+contador.toString()+"'><td><input type='radio' value="+contador+" checked id='activo"+contador.toString()+"' name='activo'></td><td><select name='tipo"+contador.toString()+"' id='tipo"+contador.toString()+"'><option value='circle'>Circulo</option><option value='rect'>Rectangulo</option></select></td><td><select name='link"+contador.toString()+"' id='link"+contador.toString()+"'><option value='correcto'>Correcta</option><option value='incorrecto'>Incorrecta</option></select></td><td><input id='title"+contador.toString()+"' type='text' class='form-control' name='title"+contador.toString()+"'></td><td><input type='text' id='coordenadas"+contador.toString()+"' class='form-control' name='coordenadas"+contador.toString()+"'></td><td><span class='input-group-addon'><button id ='button"+contador.toString()+"' name='buttton"+contador.toString()+"' class='btn btn-primary  btn-circle' type='button' onclick='Nuevasect("+contador+");'><i id ='id_icono"+contador.toString()+"' class='fa fa-plus'></i></button></span></td></tr>";
        $('tbody').append(texto);

        // Obtenemos el penultimo elemento de training phrases
        var contadorAnterior = contador-1;

        // Obtenemos el elemento del DOM del boton y del icono
        var botonAnterior = "button" + contadorAnterior.toString();
        var iconoAnterior = "id_icono" + contadorAnterior.toString();

        // Removemos la clase que tenia el boton "verde" y le colocamos el boton "rojo"
        $(`#${botonAnterior}`).removeClass('btn-primary');
        $(`#${botonAnterior}`).addClass('btn-danger');

        // Removemos la clase que tenia el icono "+" y le colocamos el icono "bote de basura"
        $(`#${iconoAnterior}`).removeClass('fa-plus');
        $(`#${iconoAnterior}`).addClass('fa-trash');

        /*  Le cambiamos el atributo "onclick" al penultimo elemento para que en lugar 
            de al darle click activar la función NuevaFrase active la función EliminarFrase 
        */
        document.getElementById(botonAnterior).setAttribute( "onClick", `EliminarSect(${contadorAnterior})`);
        
        
    }

    function EliminarSect(contadorBorrar){
        
        var conteo = document.getElementById("conteo");
        console.log("el contadoooor", contadorBorrar);
        conteo.value = parseInt(conteo.value)-1;
        var contador_aux = 1;

            // Obtenemos el div que tenemos que borrar
            idDiv = "fila" + contadorBorrar.toString();
            div = document.getElementById(idDiv);

            //Obtenemos el elemento padre del div que tenemos que borrar y eliminamos a su hijo
            padre = div.parentNode;
            padre.removeChild(div);
            

            /*  Cuando se borra un training phrases tenemos que recorrer los training phrases
                que estan despues de el para recorrer hacia abajo los elementos
            */
           console.log("antesfor");

            for(i=contadorBorrar; i >= contador_aux; i++ ){
                console.log("for");
                // Va a ser el indice del elemento que esta despues del que se eliminó
                var anterior = i+1;
                // EL elemento posterior al elemento que se borro pasará a este nuevo indice
                var nuevo = i;
                console.log("for" , anterior);
                //viejos
                actviejo = "activo"+anterior.toString();
                filaidviejo = "fila"+ anterior.toString();
                tiponameviejo= "tipo"+ anterior.toString();
                linknameviejo= "link"+ anterior.toString();
                titlenameviejo= "title"+ anterior.toString();
                targetnameviejo= "target"+anterior.toString();
                coordenadasnameviejo= "coordenadas"+ anterior.toString();
                botonviejo = "button" + anterior.toString();
                iconoviejo = "id_icono" + anterior.toString();
                console.log("*//*/*/*/*/*/*/*/*/*/*/*/*/*/*/ ",actviejo);
                //nuevos
                actnuevo = "activo"+nuevo.toString();
                filaidnuevo = "fila"+nuevo.toString();
                tiponamenuevo= "tipo"+ nuevo.toString();
                linknamenuevo= "link"+ nuevo.toString();
                titlenamenuevo= "title"+ nuevo.toString();
                targetnamenuevo= "target"+nuevo.toString();
                coordenadasnamenuevo= "coordenadas"+ nuevo.toString();
                botonnuevo = "button" + nuevo.toString();
                icononuevo = "id_icono" + nuevo.toString();
                
                //reasignamos names y ids
                activo = document.getElementById(actviejo);
                activo.value = nuevo.toString();
                activo.id = actnuevo;
                console.log(filaidviejo)
                fila = document.getElementById(filaidviejo);
                console.log(fila);
                fila.id = filaidnuevo;
                console.log(tiponamenuevo);
                tipo = document.getElementById(tiponameviejo);
                console.log(tipo);
                tipo.name=tiponamenuevo;
                tipo.id=tiponamenuevo;
                link = document.getElementById(linknameviejo);
                console.log(link);
                link.id=linknamenuevo;
                link.name=linknamenuevo;
                title = document.getElementById(titlenameviejo);
                title.id=titlenamenuevo;
                title.name=titlenamenuevo;
                
                coordenadas = document.getElementById(coordenadasnameviejo);
                coordenadas.id=coordenadasnamenuevo;
                coordenadas.name=coordenadasnamenuevo;
                boton_modificar2 = document.getElementById(botonviejo);
                boton_modificar2.id=botonnuevo;
                boton_modificar2.name=botonnuevo;
                dict[nuevo] = dict[anterior];
                delete(dict[anterior]);
                console.log(dict);
                if(i < (contador_aux-1)){
                console.log('despues if');
                document.getElementById(botonnuevo).setAttribute( "onClick", 'EliminarSect('+nuevo+')');
                }
                icono_modificar2 = document.getElementById(iconoviejo);
                icono_modificar2.id = icononuevo;
                icono_modificar2.name = icononuevo;
            }
            
    
    }
