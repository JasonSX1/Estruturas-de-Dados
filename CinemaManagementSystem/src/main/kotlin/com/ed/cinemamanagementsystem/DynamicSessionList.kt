package com.ed.cinemamanagementsystem

class DynamicSessionList: SessionDAO {

    private var ponteiroInicio: DoubleNode? = null
    private var ponteiroFim: DoubleNode? = null
    private var quantidade = 0

    override fun addSession(session: Session) {
        val tempNode = DoubleNode(session)
        tempNode.previous = null
        if (!isEmpty()) {
            tempNode.next = ponteiroInicio
            ponteiroInicio?.previous = tempNode
        } else {
            ponteiroFim = tempNode
        }
        ponteiroInicio = tempNode
        quantidade++
    }


}