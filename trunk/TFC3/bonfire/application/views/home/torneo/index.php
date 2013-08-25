
<div class="wrapper bgwhite">

	    <div id="content">
                <ul>
<?php foreach ($torneos_all as $torneo): ?>
    <li><a href="/torneo/<?php echo $torneo['id'] ?>" ><?php echo $torneo['nombre'] ?></a></li>
<?php endforeach ?>
    </ul>
                <div class="clearfix"></div>
<div class="mb20"></div>
            </div>
</div>