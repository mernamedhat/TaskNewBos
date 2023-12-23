package com.example.taskbos.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.taskbos.databinding.FragmentPhotoDetailsBinding
import com.squareup.picasso.Picasso
import kotlin.math.max
import kotlin.math.min


class PhotoDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPhotoDetailsBinding
    val args: PhotoDetailsFragmentArgs by navArgs()
    private lateinit var mScaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mScaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())
        binding.shareicon.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Task Bosta")
            var shareMessage =
                "\n\"Share Link Photo \"\n\n\n"
            shareMessage = """ ${shareMessage +args.photo} """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)

            requireActivity().startActivityForResult(
                Intent.createChooser(shareIntent, "Share file"),
                10
            )
        }
        Picasso.get().load(args.photo).into(binding.photo)
        binding.photo.setOnTouchListener { _, event ->
            mScaleGestureDetector.onTouchEvent(event)
            true
        }


    }


    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = max(0.1f, min(mScaleFactor, 10.0f))
            binding.photo.scaleX = mScaleFactor
            binding.photo.scaleY = mScaleFactor
            return true
        }
    }


}