/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */
#ifndef _decounter_H_
#define _decounter_H_

#include "kcg_types.h"

/* =====================  no input structure  ====================== */

/* ========================  context type  ========================= */
typedef struct {
  /* ---------------------------  outputs  --------------------------- */
  kcg_int /* decounter::n */ n;
  /* -----------------------  no local probes  ----------------------- */
  /* -------------------- initialization variables  ------------------ */
  kcg_bool init;
  /* ----------------------- local memories  ------------------------- */
  kcg_int /* decounter::_L6 */ _L6;
  /* -------------------- no sub nodes' contexts  -------------------- */
  /* ----------------- no clocks of observable data ------------------ */
  /* -------------------- (-debug) no assertions  -------------------- */
  /* ------------------- (-debug) local variables -------------------- */
  kcg_int /* decounter::_L8 */ _L8;
  kcg_int /* decounter::_L5 */ _L5;
  kcg_bool /* decounter::_L4 */ _L4;
  kcg_int /* decounter::_L2 */ _L2;
  kcg_int /* decounter::_L1 */ _L1;
  kcg_int /* decounter::_L9 */ _L9;
  kcg_int /* decounter::_L10 */ _L10;
} outC_decounter;

/* ===========  node initialization and cycle functions  =========== */
/* decounter */
extern void decounter(
  /* decounter::reset */kcg_bool reset,
  outC_decounter *outC);

extern void decounter_reset(outC_decounter *outC);

#endif /* _decounter_H_ */
/* $*************** KCG Version 6.1.3 (build i6) ****************
** decounter.h
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

