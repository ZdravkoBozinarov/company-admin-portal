(function(){
  const $ = (id)=>document.getElementById(id);

  const fields = {
    ext: $('extId'),
    name: $('name'),
    desc: $('description'),
    tag: $('tag'),
    nb: $('notBefore'),
    exp: $('expiry'),
    nbH: $('notBeforeHelper'),
    expH: $('expiryHelper'),
    warn: $('rangeWarn'),
    submit: $('submitBtn'),
    pv: {
      ext: $('pv-ext'), name: $('pv-name'), desc: $('pv-desc'),
      tag: $('pv-tag'), nb: $('pv-nb'), exp: $('pv-exp')
    }
  };

  // Live preview
  function syncPreview(){
    fields.pv.ext.textContent = fields.ext.value || '—';
    fields.pv.name.textContent = fields.name.value || '—';
    fields.pv.desc.textContent = fields.desc.value || '—';
    fields.pv.tag.textContent = fields.tag.value || '—';
    fields.pv.nb.textContent = fields.nb.value || '—';
    fields.pv.exp.textContent = fields.exp.value || '—';
  }

  // Epoch <-> datetime-local
  function dtLocalToEpochSeconds(dtLocal){
    if(!dtLocal) return '';
    const ms = new Date(dtLocal).getTime();
    return Number.isFinite(ms) ? Math.floor(ms/1000) : '';
  }
  function epochToDtLocal(epochSec){
    if(!epochSec) return '';
    const ms = Number(epochSec) * 1000;
    if(!Number.isFinite(ms)) return '';
    const d = new Date(ms);
    const pad=(n)=>String(n).padStart(2,'0');
    return `${d.getFullYear()}-${pad(d.getMonth()+1)}-${pad(d.getDate())}T${pad(d.getHours())}:${pad(d.getMinutes())}`;
  }

  fields.nbH.addEventListener('change', ()=>{
    const v = dtLocalToEpochSeconds(fields.nbH.value);
    if(v!==''){ fields.nb.value = v; syncPreview(); validateRange(); }
  });
  fields.expH.addEventListener('change', ()=>{
    const v = dtLocalToEpochSeconds(fields.expH.value);
    if(v!==''){ fields.exp.value = v; syncPreview(); validateRange(); }
  });

  fields.nb.addEventListener('input', ()=>{
    fields.nbH.value = epochToDtLocal(fields.nb.value);
    syncPreview(); validateRange();
  });
  fields.exp.addEventListener('input', ()=>{
    fields.expH.value = epochToDtLocal(fields.exp.value);
    syncPreview(); validateRange();
  });

  function validateRange(){
    const nb = Number(fields.nb.value);
    const exp = Number(fields.exp.value);
    const ok = Number.isFinite(nb) && Number.isFinite(exp) && exp >= nb;
    fields.warn.style.display = ok ? 'none' : 'block';
    fields.submit.disabled = !ok;
  }

  // EXT ID generator (A–Z, length 16)
  const ALPHABET = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
  function generateExtId(len=16){
    const out = [];
    if (window.crypto && crypto.getRandomValues){
      const buf = new Uint32Array(len);
      crypto.getRandomValues(buf);
      for (let i=0; i<len; i++) out.push(ALPHABET[buf[i] % 26]);
    } else {
      for (let i=0; i<len; i++) out.push(ALPHABET[Math.floor(Math.random()*26)]);
    }
    return out.join('');
  }
  function setExtId(v){
    fields.ext.value = v;
    syncPreview();
  }

  document.addEventListener('DOMContentLoaded', ()=>{
    if (!fields.ext.value) setExtId(generateExtId());
  });

  $('genExtId').addEventListener('click', ()=>{
    setExtId(generateExtId());
  });

  $('copyExtId').addEventListener('click', async ()=>{
    try{
      await navigator.clipboard.writeText(fields.ext.value);
      const btn = $('copyExtId');
      btn.textContent = 'Copied!';
      setTimeout(()=> btn.textContent = 'Copy', 1200);
    }catch(e){}
  });

  fields.ext.addEventListener('input', ()=>{
    let v = fields.ext.value.toUpperCase().replace(/[^A-Z]/g,'').slice(0,16);
    if (v !== fields.ext.value) fields.ext.value = v;
    syncPreview();
  });

  ['ext','name','desc','tag'].forEach(k=>fields[k].addEventListener('input', syncPreview));
syncPreview();
validateRange(); // извикване веднага при зареждане
})();
